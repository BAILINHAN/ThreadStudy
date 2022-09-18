package com.solo.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureFastDemo {

    public static void main(String[] args) {

        CompletableFuture<String> playerA = CompletableFuture.supplyAsync(() -> {

            System.out.println("A Come in");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {

                e.printStackTrace();
            }

            return "Player A";
        });

        CompletableFuture<String> playerB= CompletableFuture.supplyAsync(() -> {

            System.out.println("B Come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {

                e.printStackTrace();
            }

            return "Player B";
        });

        CompletableFuture<String> result = playerA.applyToEither(playerB, f -> f + " is winner");

        System.out.println(Thread.currentThread().getName() + "\t" + "------" + result.join());
    }

}
