package org.example.aIndex;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.mapping.Property;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.elasticsearch.client.RestClient;
import org.example.pojo.Student;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class IndexCreate
{
    public static void main( String[] args ) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        String apiKey = "UEMyd1FZMEIwdVBDdTJpOHBzSUw6dXp6NXRzT1ZSYTJzTXlXazhEOWQ3Zw==";

        // 创建 RestClient
        RestClient restClient = RestClient.builder(
                        HttpHost.create("https://localhost:9200")
                    )
                .setDefaultHeaders(new Header[]{
                        new BasicHeader("Authorization", "ApiKey " + apiKey)
                })
                .build();

    // 使用Jackson映射器创建传输层
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

    // 创建API客户端
        ElasticsearchClient client = new ElasticsearchClient(transport);


        CreateIndexResponse response = client.indices().create(c -> c
                .index("student")
        );
        System.out.println(response.acknowledged());
        transport.close();
        restClient.close();
    }
}
