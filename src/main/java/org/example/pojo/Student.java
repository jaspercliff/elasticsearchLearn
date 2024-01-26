package org.example.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Student {
    private Integer id;
    private String name;
    private Integer age;
    private String hobby;
}
