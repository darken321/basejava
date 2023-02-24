package com.urise.webapp;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private static int counter;
    private final AtomicInteger atomicCounter = new AtomicInteger();
    //    private static final Object LOCK = new Object();
//    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println("\nПоток через Run");
                System.out.println(getName() + ", " + getState());
            }
        };
        thread0.start();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("\nПоток через Runnable");
                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }
        });
        thread1.start();

        long now = System.currentTimeMillis();
        final MainConcurrency mainConcurrency = new MainConcurrency();
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newCachedThreadPool();

//        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            executorService.submit(() -> {
//            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
                latch.countDown();
            });
//            thread.start();
//            threads.add(thread);
        }

//        threads.forEach(thread -> {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });

        //latch.await(2, TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println(System.currentTimeMillis() - now);
        //System.out.println("Counter = " + counter);
        System.out.println("Counter = " + mainConcurrency.atomicCounter.get());
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    private void inc() {
//        lock.lock();
//        try {
//            counter++;
//        } finally {
//            lock.unlock();
//        }
    atomicCounter.incrementAndGet();
    }

}