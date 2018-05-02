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

    private String name = "哈士奇";

    private String food = "肉";

    private int age = 100;

    public Dog() {
    }
    public Dog(String food) {
        this.food = food;
    }

    //输入dog实例，输出空的，消费者
    public static void bark(Dog dog) {
        System.out.println(dog+"叫了");
    }

    /**
     * jdk默认会把当前实例传入到非静态方法，参数名为this，位置是第一个
     * 用类名对非静态方法 进行方法引用时，实际上有两个输入，一个输出
     * @param nowAge
     * @return
     */
    public int ageNow(/*Dog this,*/int nowAge) {
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
        Dog dog = new Dog();
        dog.ageNow(1);
        //函数的调用只有一个，而且函数的参数和调用的函数是同一个时，可缩写成方法引用
        //Consumer<String> consumer = s -> System.out.println(s);
        //方法的引用
        Consumer<String> consumer =  System.out::println;
        consumer.accept("接受的数据");

        //静态方法的方法引用---类名+方法名称
        Consumer<Dog>consumer1 = Dog::bark;
        consumer1.accept(dog);

        //非静态方法，使用对象实例引用---1.此处的对象 实例 的引用
        //Function<Integer, Integer> function = dog::ageNow;
        //UnaryOperator<Integer> function = dog::ageNow;
        IntUnaryOperator function = dog::ageNow;
        System.out.println("还剩下" + function.applyAsInt(5) + "岁数");
        //Consumer<Dog> consumer2 = Dog::ageNow;

        //使用类名来方法引用----2.类名
        BiFunction<Dog, Integer, Integer> eatFunction = Dog::ageNow;
        System.out.println("还剩下" + eatFunction.apply(dog,8) + "岁数");

        //构造函数的方法引用 没有输入只有输出--提供者
        Supplier<Dog> supplier = Dog::new;
        System.out.println("创建了新对象" + supplier.get());

        //带参数的构造函数的方法引用--带参数的话，就有输入和输出，就是function
        Function<String, Dog> function1 = Dog::new;
        System.out.println("创建了新对象：" + function1.apply("牛肉"));
    }
}