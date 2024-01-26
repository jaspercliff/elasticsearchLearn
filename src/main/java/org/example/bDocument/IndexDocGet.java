package org.example.bDocument;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.example.pojo.Student;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class IndexDocGet
{
    public static void main( String[] args ) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        String apiKey = "QjMxZ1JJMEJPdDE5aFlRVWlLay06WElySWFhTGtTbjZOcDlEQkxvd0hJUQ==";

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

        GetResponse<Student> response = client.get(
                g -> g.index("student").id("1"), Student.class
        );
        if (response.found()){
            Student source = response.source();
            System.out.println("source = " + source);
        }else {
            System.out.println("student not found");
        }

        transport.close();
        restClient.close();
    }
}
