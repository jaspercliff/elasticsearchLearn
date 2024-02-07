package org.example.bDocument;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.UpdateResponse;
import org.example.pojo.Student;
import org.example.utils.ConnectUtils;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class IndexDocUpdate
{
    public static void main( String[] args ) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        ElasticsearchClient client = ConnectUtils.getClient();

        Student student = new Student().setId(1).setName("cliff").setAge(12).setHobby("smoke");

        UpdateResponse<Student> student1 = client.update(c -> c
                .index("student").
                id(student.getId().toString())
                        .upsert(student)
                ,
                Student.class
        );
        System.out.println("student1 = " + student1);
    }
}
