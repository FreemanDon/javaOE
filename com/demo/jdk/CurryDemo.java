package com.demo.jdk;

import java.util.function.Function;

/**
 * @author DJ
 * @version V1.0
 * @Package: com.demo.jdk
 * @Description: 级联表达式(多个箭步的lambda表达式)和柯里化
 * 柯里化：把多个参数的函数转换为只有一个参数的函数
 * 柯里化的目的：函数标准化（方便统一处理）  前台开发js原生语法 var a = add(2)(3)(4)
 * 高阶函数：返回函数的函数
 * @Creation Date:2018-05-04
 */

public class CurryDemo {
    public static void main(String[] args) {
        //箭头左边输入，右边输出.实现了x+y的级联表达式
        Function<Integer,Function<Integer,Integer>>function = x ->y -> x + y;
        System.out.println(function.apply(2).apply(3));

        Function<Integer, Function<Integer, Function<Integer, Integer>>> function1
                = x -> y -> z -> x + y + z;
        System.out.println(function1.apply(2).apply(3).apply(4));

        int[] nums = {3, 4, 5};
        Function fun = function1;
        for (int num : nums) {
            if (fun instanceof Function) {
                Object o = fun.apply(num);
                System.out.println(o);
                if (o instanceof Function) {
                    fun = (Function) o;
                    System.out.println(fun);
                } else {
                    System.out.println("调用结束：结果为" + o);
                }
            }
        }
    }
}
