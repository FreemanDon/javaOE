package com.demo.jdk;

import java.util.function.Consumer;

/**
 * @author DJ
 * @version V1.0
 * @Package: com.demo.jdk
 * @Description: 变量引用
 * @Creation Date:2018-05-04
 */
public class VarDemo {
    public static void main(String[] args) {
        String str = "我们的时间";
        Consumer<String> consumer = s -> System.out.println(s + str);
        consumer.accept("233");
    }
}
