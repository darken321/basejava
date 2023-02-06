package com.urise.webapp.util;

public class MainConcurrency {
    private static int counter;
    private static final Object LOCK = new Object();

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
        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            thread.join();
        }
        Thread.sleep(500);
        System.out.println(System.currentTimeMillis() - now);
        System.out.println("Counter = " + counter);
    }

    private synchronized void  inc() {
                counter++;
    }
}
