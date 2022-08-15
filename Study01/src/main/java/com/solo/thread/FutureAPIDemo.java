package com.solo.thread;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureAPIDemo {

    public static void main(String[] args) {

        FutureTask<String> futureTask = new FutureTask<String>(

                () -> {

                    System.out.println(Thread.currentThread().getName() + "\t -----come in");

                    TimeUnit.SECONDS.sleep(5);

                    return "task over";
                }

        );

        Thread t1 = new Thread(futureTask, "t1");
        t1.start();

        //此时任务没有处理完 会阻塞线程 其他线程不会执行
        try {
            //get()方法调用时，必须等待到结果才会执行其他线程
//            System.out.println(futureTask.get());
            //当设置了等待之间，等待之间内没有拿到结果，则会抛出TimeoutException异常
//            System.out.println(futureTask.get(3, TimeUnit.SECONDS));

            System.out.println(Thread.currentThread().getName() + "\t -----在忙别的");

            while (true){

                //判断异步任务是否已经完成
                if(futureTask.isDone()){

                    System.out.println(futureTask.get());
                    break;
                }else {

                    //暂停毫秒
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    System.out.println("正在处理中......");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

/**
 * 1 get()方法容易导致阻塞，一般建议放在程序后面，一旦调用必须等待到结果，容易导致程序阻塞
 * 2 假如不愿意等待长时间，希望过时不候，可以自动离开
 */
