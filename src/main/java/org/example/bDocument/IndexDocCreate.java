package org.example.bDocument;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
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
import org.example.utils.ConnectUtils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class IndexDocCreate
{
    public static void main( String[] args ) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        ElasticsearchClient client = ConnectUtils.getClient();

        Student student = new Student().setId(1).setName("jasper").setAge(12).setHobby("smoke");

        IndexResponse student1 = client.index(c -> c
                .index("student").id(student.getId().toString())
                .document(student)
        );
        System.out.println("student1 = " + student1);
    }
}
