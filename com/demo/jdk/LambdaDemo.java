package com.demo.jdk;

/**
 * @author Don
 * @version V1.0
 * @Package: com.demo.jdk
 * @Description: 返回实现了 指定接口的 对象实例
 * 函数接口@FunctionalInterface 函数接口，告诉他人不要再添加了
 * 接口里面必须只有一个要 实现 的方法
 * 设计思想-单一责任制 一个接口一个事情
 * @Creation Date:2018-04-29
 */
@FunctionalInterface
interface Interface1{
    int doubleNum(int i);

    //jdk8新增的方法default，接口里面默认实现方法
    default int add(int x, int y) {
        return x + y;
    }
}

@FunctionalInterface
interface Interface2{
    int doubleNum(int i);

    //jdk8新增的方法default，接口里面默认实现方法
    default int add(int x, int y) {
        return x + y;
    }
}

@FunctionalInterface
interface Interface3 extends Interface2,Interface1{

    @Override
    default int add(int x, int y) {
        return Interface1.super.add(x, y);
    }
}


public class LambdaDemo {
    public static void main(String[] args) {
        Interface1 i1 = (i) -> i * 2;
        //常见写法
        Interface1 i2 = i -> i * 2;

        Interface1 i3 = (int i) -> i * 2;

        Interface1 i4 = (int i) -> {
            System.out.println("-------多行---------");
             return i * 2;
        };
        System.out.println(i1.add(3, 7));
        System.out.println(i1.doubleNum(20));
    }
}