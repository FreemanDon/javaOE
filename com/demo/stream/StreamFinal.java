package com.demo.stream;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author DJ
 * @version V1.0
 * @Package: com.demo.stream
 * @Description: Stream流编程-终止操作
 *
 * 非短路操作：得到数据，暂不结束操作。带order一般与并行流相关
 * forEach/forEachOrdered
 * collect/toArray
 * reduce: 将流合成一个数据
 * min/max/count
 *
 * 短路操作：得到数据，结束操作
 * findFirst/findAny
 * allMatch/anyMatch/noneMatch
 *
 * @Creation Date:2018-05-07
 */

public class StreamFinal {
    public static void main(String[] args) {
        String str = "my name is 007";
        //使用并行流
        str.chars().parallel().forEach(i-> System.out.print((char)i));

        //使用forEachOrdered保证顺序
        System.out.println();
        System.out.println("---------使用forEachOrdered保证顺序-------");
        str.chars().parallel().forEachOrdered(i -> System.out.print((char) i));

        //收集到list
        System.out.println();
        System.out.println("-------收集到list--------");
        List<String> list = Stream.of(str.split(" ")).collect(Collectors.toList());
        System.out.println(list);

        //使用reduce拼接字符串
        Optional<String> letters = Stream.of(str.split(" "))
                .reduce((s1, s2) -> s1 + "|" + s2);
        System.out.println(letters.orElse("没有呢"));

        //计算总长度
        Integer reduce = Stream.of(str.split(" "))
                .map(s -> s.length()).reduce(0, (s1, s2) -> s1 + s2);
        System.out.println(reduce);

        //max的使用
        Optional<String> max = Stream.of(str.split(" ")).max((s1, s2) -> s1.length() - s2.length());
        System.out.println(max.get());

        //使用短路操作
        OptionalInt first = new Random().ints().findFirst();
        System.out.println(first.getAsInt());
    }
}
