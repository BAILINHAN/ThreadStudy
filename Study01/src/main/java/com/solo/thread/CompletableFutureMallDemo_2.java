package com.solo.thread;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 案例说明：电商比价需求，模拟如下情况
 *
 * 需求：
 * 1. 同一款产品，同时搜索出同款产品在各大电商平台的售价
 * 2. 同一款产品，同时搜索出本产品在同一个电商平台下，各个入驻商家售价是多少
 * 3. 输出结果希望是同款产品在不同地方的价格清单列表，返回List<String>
 *     《MySql》in jd price is 88.05
 *     《MySql》in dd price is 86.11
 *     《MySql》in tb price is 90.43
 * 4. 使用函数式编程，链式编程，Stream流式计算
 *
 */
public class CompletableFutureMallDemo_2 {

    static List<NetMall> netMallList = Arrays.asList(

            new NetMall("jd"),
            new NetMall("tb"),
            new NetMall("dd")

    );

    /**
     * step by step
     * List<NetMall> ----> map ----> List<String>
     * @param list
     * @param productName
     * @return
     */
    public static List<String> getPrice(List<NetMall> list, String productName){

        return list.stream().map(s -> String.format(productName + " in %s price is %.2f",
                s.getNerMall(), s.calcPrice(productName))).collect(Collectors.toList());
    }

    /**
     * List<NetMall> ----> ???? ----> List<String>
     * @param list
     * @param productName
     * @return
     */
    public static List<String> getPriceByCompletableFuture(List<NetMall> list, String productName){

        return list.stream().map(l -> CompletableFuture.supplyAsync(
                () -> String.format(productName + " in %s price is %.2f",
                        l.getNerMall(), l.calcPrice(productName))
        )).collect(Collectors.toList()).stream().map(s -> s.join()).collect(Collectors.toList());
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        List<String> priceList = getPrice(netMallList, "MySql");

        //获取价格在商城中的列表
        priceList.forEach(System.out::println);
        long end = System.currentTimeMillis();

        System.out.println("----cost time:" + (end - start));

        long startTime = System.currentTimeMillis();

        System.out.println("-----------------------------------------------------------------");

        List<String> priceListByCompletableFuture = getPriceByCompletableFuture(netMallList, "MySql");
        //获取价格在商城中的列表

        priceListByCompletableFuture.forEach(System.out::println);
        long endTime = System.currentTimeMillis();

        System.out.println("----cost time:" + (endTime - startTime));
    }
}

@Data
class NetMall{

    private String nerMall;

    public NetMall(String nerMall) {
        this.nerMall = nerMall;
    }

    public BigDecimal calcPrice(String productName){

        try {

            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            e.printStackTrace();
        }

        double price = ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);

        return new BigDecimal(price);
    }

}