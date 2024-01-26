package org.example.utils;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;

public class ConnectUtils {
    public static ElasticsearchClient getClient(){
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
        return new ElasticsearchClient(transport);
    }
}
