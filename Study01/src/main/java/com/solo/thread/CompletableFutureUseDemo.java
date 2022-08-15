package com.solo.thread;

import java.util.Objects;
import java.util.concurrent.*;

public class CompletableFutureUseDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        CompletableFuture.supplyAsync(() -> {

            System.out.println(Thread.currentThread().getName() + "---come in");
            //随机获取10以内的数字
            int result = ThreadLocalRandom.current().nextInt(10);

            //暂停线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("1秒后打印结果:" + result);
            if(result > 5){

                int i = 10 /0;
            }
            return result;

        }, executorService).whenComplete((r, e) -> {

            if(Objects.isNull(e)){

                System.out.println("----计算完成，update: " + r);
            }
        }).exceptionally(e -> {

            e.printStackTrace();
            System.out.println("异常情况：" + e.getCause() + "\t" + e.getMessage());
            return null;
        });

        System.out.println(Thread.currentThread().getName() + " do other task");

        executorService.shutdown();

        //主线程不能立即结束，否则会关闭CompletableFuture默认使用的守护线程，让主线程暂停3秒钟
        /*try {

            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }*/

        //future1();
    }

    private static void future1() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(

                () -> {

                    System.out.println(Thread.currentThread().getName() + "---come in");
                    //随机获取10以内的数字
                    int result = ThreadLocalRandom.current().nextInt(10);

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("1秒后打印结果:" + result);
                    return result;
                }
        );

        System.out.println(Thread.currentThread().getName() + " do other task");
        //打印结果
        System.out.println(completableFuture.get());
    }

}
