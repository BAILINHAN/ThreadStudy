package com.solo.thread;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureAPIDemo3 {

    public static void main(String[] args) {

        /*
            CompletableFuture.supplyAsync(

                    () -> {

                        return 1;
                    }
            ).thenApply(
                    f -> {

                    return f + 2;
            }).thenApply(
                    f -> {
                        return f + 3;
                    }
            ).thenAccept(
                    *//*r -> {
                        //结果为 1 + 2 + 3
                        System.out.println(r);
                    }*//*
                    System.out::println

            );
        */

        System.out.println(CompletableFuture.supplyAsync(
                () -> "result A"
        ).thenRun(
                () -> {

                }
        ).join());

        System.out.println(CompletableFuture.supplyAsync(
                () -> "result A"
        ).thenAccept(
                r -> System.out.println(r)
        ).join());

        //上一步的 result A 赋值给r, r + "result B" 组成新的r join()获取返回值
        System.out.println(CompletableFuture.supplyAsync(
                () -> "result A"
        ).thenApply(
                r -> r + "result B"
        ).join());
    }

}
