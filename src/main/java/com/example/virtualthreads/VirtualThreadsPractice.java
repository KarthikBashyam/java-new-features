package com.example.virtualthreads;

import java.util.concurrent.CountDownLatch;

public class VirtualThreadsPractice {

    public static void main(String[] args) throws Exception{

        System.out.println("Hello world!");

        var latch = new CountDownLatch(1);

        Thread.startVirtualThread(() -> {
                    System.out.println("Welcome to Virtual Threads");
                    latch.countDown();
                }
        );

        latch.await();
        new StructureConcurrency().shutdownOnFailure();

    }
}