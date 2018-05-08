package com.demo.stream;

import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * @author DJ
 * @version V1.0
 * @Package: com.demo.stream
 * @Description: stream流的基本操作
 * @Creation Date:2018-05-07
 */
public class StreamDemo {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        /**
         * 外部迭代
         * 串行，过程繁琐（线程池）
         *只需要使用并行流
         */
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        System.out.println("结果为：" + sum);

        /**
         * 使用stream的内部迭代
         * 告诉得到什么，不关注如何得到
         * 使用内部迭代，可以使用高级的特性，惰性求值，并行，短路操作（符合条件的结果得出）
         */
        int sum1 = IntStream.of(nums).sum();
        System.out.println(sum1);

        /**
         * 中间操作/终止操作，惰性求值
         * map就是中间操作（返回stream流的操作）（没有终止操作，map便暂不执行）
         * sum就是终止操作
         */
        int sum2 = IntStream.of(nums).map(t -> t * 2 - 1).sum();
        System.out.println(sum2);
        System.out.println("惰性求值就是终止没有调用的情况下，中间操作不会执行");
        OptionalInt min = IntStream.of(nums).map(StreamDemo::doubleNum).min();
        System.out.println("数组中最小值是：" + min.getAsInt());
        int sum3 = IntStream.of(nums).map(StreamDemo::doubleNum).sum();
        System.out.println("得出的结果是：" + sum3);

        //OptionalInt的特性自我实验
        OptionalInt optionalInt = OptionalInt.of(2);
        if (optionalInt.isPresent()) {
            System.out.println(optionalInt.getAsInt());
        } else {
            System.out.println("没有值传递为：" + optionalInt.isPresent());
        }
        optionalInt.ifPresent((total) -> System.out.println("测试的value:" + total));
    }

    public static int doubleNum(int i) {
        System.out.println("执行了" + i + "乘以3");
        return i * 3;
    }
}
