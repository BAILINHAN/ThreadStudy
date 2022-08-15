package com.solo.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CompletableFutureDemo {

    public static void main(String[] args) {

        FutureTask<String> futureTask = new FutureTask<>(new MyThread());

        Thread t1 = new Thread(futureTask);
        t1.start();

        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}

//有返回值 会抛出异常
//特点 多线程 有返回值 异步任务
class MyThread implements Callable<String>{

    @Override
    public String call() throws Exception {

        System.out.println("-----come in call()");
        return "hello Callable";
    }
}

//没有返回值 没有抛出异常
class MyThread2 implements Runnable{

    @Override
    public void run() {

    }
}
