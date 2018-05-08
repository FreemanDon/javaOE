package com.demo.jdk;

import java.util.stream.IntStream;

/**
 * @author Don
 * @version V1.0
 * @Package: com.demo.jdk
 * @Description: TODO
 * @Creation Date:2018-04-29
 */
public class demo01 {
    public static void main(String[] args) {
        //数亿条数据时，for循环（单线程）性能不达标
        //可以创建线程池，将下列拆分，快速排序的思想，之后递归 得到最小值
        int[] nums = {33, 55, -55, 90, -666, 90};
        int min = Integer.MAX_VALUE;
        for (int i : nums) {
            if (i < min) {
                min = i;
            }
        }
        System.out.println(min);

        //jdk8
        int asInt = IntStream.of(nums).min().getAsInt();
        //这个流会并行执行（包括多线程，线程池，拆分等）
        int asInt1 = IntStream.of(nums).parallel().min().getAsInt();
        System.out.println(asInt);
    }
}