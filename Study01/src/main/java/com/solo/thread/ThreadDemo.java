package com.solo.thread;

import java.util.concurrent.TimeUnit;

public class ThreadDemo {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {

            System.out.println(Thread.currentThread().getName() + "\t 开始运行, " +
                    (Thread.currentThread().isDaemon() ? "守护线程" : "用户线程"));

            while (true) {

            }

        }, "t1");

        //将线程t1设置为守护线程 设置守护线程必须在该线程start()方法之前设置
        t1.setDaemon(true);
        t1.start();

        try {

            TimeUnit.SECONDS.sleep(3);

        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "\t ----end 主线程");

    }

}
