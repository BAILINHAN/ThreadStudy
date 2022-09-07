package com.solo.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureAPIDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return "abc";
                }
        );

//        System.out.println(completableFuture.get());
        //等待两秒钟
//        System.out.println(completableFuture.get(2L, TimeUnit.SECONDS));
//        System.out.println(completableFuture.join());
        //取值时如果还没有计算完成，返回valueIfAbsent的值
//        System.out.println(completableFuture.getNow("xxx"));
        //输出是否打断，如果打断为true，不打断为false，取到计算结果则返回计算记过，未取到则返回参数设定的值
        System.out.println(completableFuture.complete("completeValue") + "\t" + completableFuture.join());
    }

}
