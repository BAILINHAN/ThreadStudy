package com.solo.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 1. 没有传入自定义的线程池，都用默认线程池ForkJoinPool
 * 2. 当传入一个自定义的线程池时：
 *    ① 如果执行第一个任务时，传入了自定义的线程池，调用thenRun方法执行第二个任务时，
 *    第二个任务和第一个任务共用同一个线程池
 *    ② 如果执行第一个任务时，传入了自定义的线程池，调用thenRunAsync执行第二个任务时，
 *    则第一个任务使用的是自己传入的线程池，第二个任务使用的是ForkJoin线程池
 *    ③ 可能系统处理太快，系统优化切换原则，直接使用main线程处理
 */
public class CompletableFutureWIthThreadPoolDemo {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        try {

            CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {

                try {

//                    TimeUnit.MILLISECONDS.sleep(20);
                }catch (Exception e){

                    e.printStackTrace();
                }
                System.out.println("1号任务" + "\t" + Thread.currentThread().getName());
                return "abcd";
            }, threadPool).thenRun(() -> {

                try {

                    TimeUnit.MILLISECONDS.sleep(20);
                }catch (Exception e){

                    e.printStackTrace();
                }
                System.out.println("2号任务" + "\t" + Thread.currentThread().getName());
            }).thenRun(() -> {

                try {

                    TimeUnit.MILLISECONDS.sleep(10);
                }catch (Exception e){

                    e.printStackTrace();
                }
                System.out.println("3号任务" + "\t" + Thread.currentThread().getName());
            }).thenRun(() -> {

                try {

                    TimeUnit.MILLISECONDS.sleep(10);
                }catch (Exception e){

                    e.printStackTrace();
                }
                System.out.println("4号任务" + "\t" + Thread.currentThread().getName());
            });

            //等待2s读取结果
            System.out.println(voidCompletableFuture.get(2L, TimeUnit.SECONDS));

        }catch (Exception e){

            e.printStackTrace();
        }finally {

            threadPool.shutdown();
        }


    }
    
}
