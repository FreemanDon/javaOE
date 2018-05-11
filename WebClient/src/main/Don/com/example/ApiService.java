package com.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author DJ
 * @version V1.0
 * @Package: com.example.demo
 * @Description: 服务器相关信息
 * @Creation Date:2018-05-11
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiService {

    String value() default "";
}
