package com.demo.jdk;

import java.util.function.*;

/**
 * @author Don
 * @version V1.0
 * @Package: com.demo.jdk
 * @Description: TODO
 * @Creation Date:2018-04-29
 */
class Dog{

    private String name = "哮天犬";
    private String food = "肉";
    private int age = 100;


    public static void bark(Dog dog) {
        System.out.println(dog+"叫了");
    }

    public int ageNow(int nowAge) {
        System.out.println("吃了"+food);
        System.out.println("今年" + nowAge + "岁");
        this.age -= nowAge;
        return this.age;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
public class MethodRefrenceDemo {

    public static void main(String[] args) {
//        Consumer<String> consumer = s -> System.out.println(s);
        //方法的引用
        Consumer<String> consumer =  System.out::println;
        consumer.accept("接受的数据");

        //静态方法的方法引用
        Consumer<Dog>consumer1 = Dog::bark;
        Dog dog = new Dog();
        consumer1.accept(dog);

        //非静态方法，使用对象实例引用--此处的对象的引用
//        Function<Integer, Integer> function = dog::ageNow;
//        UnaryOperator<Integer> function = dog::ageNow;
        IntUnaryOperator function = dog::ageNow;
        System.out.println("还剩下" + function.applyAsInt(5) + "岁数");
//        Consumer<Dog> consumer2 = Dog::ageNow;
    }
}