package com.solo.thread;

import java.util.concurrent.*;

public class CompletableFutureBuildDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //无返回值
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(

                () -> {

                    System.out.println(Thread.currentThread().getName());
                    //暂停几秒钟线程
                    try {

                        TimeUnit.SECONDS.sleep(1);
                    }catch (Exception e){

                        e.printStackTrace();
                    }
                }, executorService //使用自己的线程池

        );

        //无返回值 为null
        System.out.println(voidCompletableFuture.get());

        //有返回值
        CompletableFuture<String> objectCompletableFuture = CompletableFuture.supplyAsync(

                () -> {

                    System.out.println(Thread.currentThread().getName());
                    //暂停几秒钟线程
                    try {

                        TimeUnit.SECONDS.sleep(1);
                    }catch (Exception e){

                        e.printStackTrace();
                    }
                    return "hello supplyAsync";
                }

        , executorService);
        System.out.println(objectCompletableFuture.get());

        executorService.shutdown();

    }

}
