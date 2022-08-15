package com.solo.thread;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureMallDemo {

    @SneakyThrows
    public static void main(String[] args) {

        Student student = new Student();
        student.setId(1);
        student.setStudentName("z3");
        student.setMajor("cs");
        
        student.setId(12).setMajor("english").setStudentName("li4");

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {

            return "hello 1234";
        });

        //编译时报出异常
//        System.out.println(completableFuture.get());
        //在编译时不报出异常
        System.out.println(completableFuture.join());

    }

}

@Data
@Accessors(chain = true)
class Student{

    private Integer id;
    private String studentName;
    private String major;

}