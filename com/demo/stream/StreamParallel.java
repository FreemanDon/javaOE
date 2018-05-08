package com.demo.stream;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author DJ
 * @version V1.0
 * @Package: com.demo.stream
 * @Description: 并行器
 * @Creation Date:2018-05-07
 */
public class StreamParallel {
    public static void main(String[] args) {
        //1.调用parallel 产生一个并行流
        //IntStream.range(1, 100).parallel().peek(StreamParallel::debug).count();

        /*//现在要实现一个这样的效果：先并行，再串行
        //2.多次调用 parallel/sequential 以最后一次调用为准
        IntStream.range(1, 100)
                //并行流
                .parallel().peek(StreamParallel::debug)
                //串行流
                .sequential().peek(StreamParallel::debug1)
                .count();*/

        //3.根据输出结果可得:并行流使用的线程池，ForkJoinPool.commonPool
        //默认的性能数是当前cpu的个数
        //使用这个属性可以修改默认的线程数
        //System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
        //IntStream.range(1, 100).parallel().peek(StreamParallel::debug2).count();

        //4.并行流 使用相同线程池会发生阻塞，可以使用自己的线程池，避免阻塞
        ForkJoinPool pool = new ForkJoinPool(20);
        pool.submit(() -> IntStream.range(1, 100).parallel().peek(StreamParallel::debug).count());
        pool.shutdown();

        //避免主线程退出
        synchronized (pool) {
            try {
                pool.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public static void debug(int i) {
        System.out.println("debug" + i);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void debug1(int i) {
        System.err.println("debug1" + i);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void debug2(int i) {
        System.err.println(Thread.currentThread().getName()+"debug2" + i);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
