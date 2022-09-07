package com.solo.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletableFutureAPIDemo2 {

    /**
     * thenApply:
     * 计算结果存在依赖关系，多个线程之间串行化
     * 当一个线程出现或异常时，不会执行到下一步，直接报错
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
        ).thenApply(
                f -> {

                    System.out.println("第2步");
                    return f + 2;
                }
                //thenApply 将上一步的运算结果传递给下一步
        ).thenApply(
                f -> {
                    
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
