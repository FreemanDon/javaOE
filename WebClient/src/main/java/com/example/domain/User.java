package com.example.domain;

import lombok.Data;

/**
 * @author DJ
 * @version V1.0
 * @Package: com.example.domain
 * @Description: MongoDB表中实体类
 * @Creation Date:2018-05-08
 */
@Data
public class User {
    private String id;
    private String name;
    private int age;
}
