package com.demo.jdk;

import java.util.function.Function;

/**
 * @author DJ
 * @version V1.0
 * @Package: com.demo.jdk
 * @Description: 级联表达式(多个箭步的lambda表达式)和柯里化
 * @Creation Date:2018-05-04
 */

public class CurryDemo {
    public static void main(String[] args) {
        //箭头左边输入，右边输出.实现了x+y的级联表达式
        Function<Integer,Function<Integer,Integer>>function = x ->y -> x + y;
        System.out.println(function.apply(2).apply(3));
    }
}
