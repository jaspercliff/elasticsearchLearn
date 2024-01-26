package org.example.aIndex;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.elasticsearch.client.RestClient;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class IndexSearch
{
    public static void main( String[] args ) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();
        basicCredentialsProvider.setCredentials(
                AuthScope.ANY,
                new UsernamePasswordCredentials("elastic", "k0qUzfRUmeJcfiyvFsDh"));
//        HTTPS 就是在 HTTP 协议上加入了 TLS/SSL 层
        // 配置 SSL 上下文以支持 HTTPS
        SSLContext sslContext = SSLContextBuilder.create().loadTrustMaterial((chain, authType) -> true).build();

        // 创建 RestClient
        RestClient restClient = RestClient.builder(
                        new HttpHost("127.0.0.1", 9200, "https")) // 使用 HTTPS 协议
                .setRequestConfigCallback(
                        requestConfigBuilder -> requestConfigBuilder)
                .setHttpClientConfigCallback(
                        httpAsyncClientBuilder -> httpAsyncClientBuilder
                                .setDefaultCredentialsProvider(basicCredentialsProvider)
                                .setSSLContext(sslContext) // 设置 SSL 上下文
                )
                .build();

        // 使用Jackson映射器创建传输层
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        // 创建API客户端
        ElasticsearchClient client = new ElasticsearchClient(transport);

//        SearchResponse<Object> demo = client.search(c -> c.index("demo1"),Object.class);
//        System.out.println("demo = " + demo);
//        List<Hit<Object>> documents = demo.hits().hits();
//        System.out.println("documents = " + documents);

        GetIndexResponse demo = client.indices().get(c -> c.index("demo"));
        System.out.println("demo = " + demo.result().keySet());

        transport.close();
        restClient.close();
    }
}
