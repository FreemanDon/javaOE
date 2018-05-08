package com.demo.stream;

/**
 * @author DJ
 * @version V1.0
 * @Package: com.demo.stream
 * @Description: Stream流编程-创建
 * @Creation Date:2018-05-07
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 集合：Collection.stream,parallelStream
 * 数组：Arrays.stream
 * 数字Stream：IntStream/DoubleStream/LongStream.range/rangeClosed(单独创建的流，无需自动装箱，性能更高)
 *            Random.ints/longs/doubles(无限的数字流)
 * 自己创建：Stream.generate/iterate
 */
public class StreamCreate {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        //从集合创建
        list.stream();
        list.parallelStream();

        //从数组创建
        Arrays.stream(new int[]{2, 4, 6});

        //创建数字流 相关的结果
        IntStream.of(1, 3, 5);
        //创建一个1-10的数字流
        IntStream.rangeClosed(1, 10);

        //使用random 实例方法 创建一个无限流,使用limit限制做一个短路终止，否则易报错
        new Random().ints().limit(10);

        //自己创建
        Random random = new Random();
        Stream.generate(() -> random.nextInt()).limit(10);
    }

}
