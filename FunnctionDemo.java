package com.demo.jdk;

import java.util.function.Consumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * @author Don
 * @version V1.0
 * @Package: com.demo.jdk
 * @Description: TODO
 * @Creation Date:2018-04-29
 */
public class FunnctionDemo {
    public static void main(String[] args) {
        //断言函数接口
//        Predicate<Integer> predicate = i -> i > 0;
        IntPredicate predicate = i -> i > 0;
        System.out.println(predicate.test(-9));

        //消费函数接口
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("输入的数据");
    }
}