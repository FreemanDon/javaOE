package com.demo.jdk;

/**
 * @author Don
 * @version V1.0
 * @Package: com.demo.jdk
 * @Description: TODO
 * @Creation Date:2018-04-29
 */
public class ThreadDemo {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("ok");
            }
        };
        new Thread(runnable).start();

        //jdk8 lambda
        Runnable target1 = () -> System.out.println("ok");
        Runnable target2 = () -> System.out.println("ok");
        System.out.println(target1 == target2);//false
        new Thread(target1).start();
    }
}