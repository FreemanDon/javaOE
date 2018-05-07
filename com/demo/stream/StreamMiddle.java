package com.demo.stream;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author DJ
 * @version V1.0
 * @Package: com.demo.stream
 * @Description: Stream流-中间操作
 * 无状态：当前结果和其他无关系
 * map/mapToXxx（得到其中的信息double,int,long）根据string对象得到其中的一个属性，这个属性是个int类型
 * flatMap/flatMapToXxx A对象下面有个B属性，这个属性是个集合 得到所有A对象里面所有B属性的列表
 * filter 过滤器
 * peek 入参是个消费者，和foreach相像，不过foreach是终止操作，peek是中间操作
 * unordered 并行流 用的不多
 *
 * 有状态：结果需要依赖其他元素 distinct（防重复）,sorted（排序），limit（限流）/skip（跳过） 返回个string
 *
 * @Creation Date:2018-05-07
 */
public class StreamMiddle {
    public static void main(String[] args) {
        String str = "my name is 007";
        //打印每个单词的长度
        Stream.of(str.split(" ")).filter(s -> s.length() > 2).map(s -> s.length())
                .forEach(System.out::println);

        //flatMap A->B 属性（是个集合）最终的刀所有A元素里面所有B属性集合
        //********intStream/longStream并不是Stream的子类，所以要进行装箱boxed******
        Stream.of(str.split(" ")).flatMap(s -> s.chars().boxed())
                .forEach(i -> System.out.print((char)i.intValue()));

        //peek 用于debug。是个中间操作，和foreach是终止操作
        System.out.println();
        System.out.println("----------peek-----------");
        Stream.of(str.split(" ")).peek(System.out::println)
                .forEach(System.out::println);

        //limit使用 , 主要用于无限流
        System.out.println("------------limit--------------");
        new Random().ints().filter(i -> i > 100 && i < 1000)
                .limit(10).forEach(System.out::println);
    }
}
