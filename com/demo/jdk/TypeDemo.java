package com.demo.jdk;

/**
 * @author DJ
 * @version V1.0
 * @Package: com.demo.jdk
 * @Description: 类型推断
 * @Creation Date:2018-05-04
 */

@FunctionalInterface
interface IMath {
    int add(int a, int b);
}

@FunctionalInterface
interface IMath2 {
    int del(int a, int b);
}
public class TypeDemo {
    public static void main(String[] args) {

        //变量类型定义
        IMath lambda = (x, y) -> x + y;

        //数组里
        IMath[] lambda1 = {(x, y) -> x + y};

        //强转
        Object lambda2 = (IMath)(x, y) -> x + y;

        //通过返回类型
        IMath createLambda = createLambda();

        TypeDemo typeDemo = new TypeDemo();
        //当有二义性的时候，使用强转对应的接口解决
        typeDemo.test((IMath) (x, y) -> x + y);
    }

    public void test(IMath math) {

    }

    public void test(IMath2 math2) {

    }

    public static IMath createLambda() {
        return ((x, y) -> x + y);
    }
}
