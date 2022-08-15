package com.solo.thread;

import java.util.concurrent.*;

public class FutureThreadPoolDemo {

    public static void main(String[] args) {

        //3个任务，目前开启多个异步任务线程来处理，请问耗时多少

        long startTime = System.currentTimeMillis();

        //使用线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        FutureTask futureTask1 = new FutureTask<String>(

                () -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return "task1 over";

                }
        );

        FutureTask futureTask2 = new FutureTask<String>(

                () -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(300);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return "task2 over";

                }
        );

        //异步线程1进行1号任务
        executorService.submit(futureTask1);
        //异步线程2进行2号任务
        executorService.submit(futureTask2);

        //获取1号任务返回值
        try {
            System.out.println(futureTask1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //获取2号任务返回值
        try {
            System.out.println(futureTask2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //main线程进行3号任务
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        executorService.shutdown();

        long endTime = System.currentTimeMillis();

        System.out.println("----costTime: " + (endTime - startTime) + "毫秒");

        System.out.println(Thread.currentThread().getName() + "\t ---end");

    }

    private static void m1() {
        //3个任务,目前只有一个线程main来处理，请问耗时多少

        long startTime = System.currentTimeMillis();

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            TimeUnit.MILLISECONDS.sleep(300);
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            TimeUnit.MILLISECONDS.sleep(300);
        }catch (Exception e){
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("----costTime: " + (endTime - startTime) + "毫秒");

        System.out.println(Thread.currentThread().getName() + "\t ---end");
    }

}
