package org.example.bDocument;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import org.example.utils.ConnectUtils;

import java.io.IOException;

public class IndexDocDelete {
    public static void main(String[] args) throws IOException {
        ElasticsearchClient client = ConnectUtils.getClient();
        DeleteResponse student = client.delete(
                d -> d.index("student").id("1")
        );
        System.out.println("delete = " + student);
    }
}
