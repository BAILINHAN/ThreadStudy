package com.solo.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletableFutureAPIWithHandler {

    /**
     * handler:
     * 有异常也可以继续执行后面的代码，根据带的异常参数可以做进一步处理
     * @param args
     */
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        CompletableFuture.supplyAsync(

                () -> {

                    try {

                        TimeUnit.SECONDS.sleep(1);

                    }catch (InterruptedException e){

                        e.printStackTrace();
                    }

                    System.out.println("第1步");
                    return 1;
                }, executorService
        ).handle(
                (f, e) -> {

                    int i = 2 / 0;
                    System.out.println("第2步");
                    return f + 2;
                }
                //thenApply 将上一步的运算结果传递给下一步
        ).handle(
                (f, e) -> {

                    System.out.println("第3步");
                    return f + 3;
                }
        ).whenComplete(
                (v, e) ->{
                    if(e == null){
                        System.out.println("计算结果：" + v);
                    }

                }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });

        System.out.println(Thread.currentThread().getName() + "----主线程干其他任务");

        executorService.shutdown();
    }

}
