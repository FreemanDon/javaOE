package com.example.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

/**
 * @author DJ
 * @version V1.0
 * @Package: com.example.domain
 * @Description: MongoDB表中实体类
 * @Creation Date:2018-05-08
 */
//MongoDB中的表名
@Document(collection = "user")
@Data
public class User {
    @Id
    private String id;

    @NotBlank
    private String name;

    @Range(min = 10, max = 50)
    private int age;
}
