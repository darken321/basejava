package com.urise.webapp.util;

public class LazySingleton {
    static class Friend {
        private final String name;
        public Friend(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public synchronized void methodFirst(Friend friend) {
            System.out.println(this.name + " call first method to " + friend.getName());
            friend.methodSecond(this);
        }
        public synchronized void methodSecond(Friend friend) {
            System.out.format(this.name + " call second method to " + friend.getName());
        }
    }

    public static void main(String[] args) {
        final Friend friend1 = new Friend("friend1");
        final Friend friend2 = new Friend("friend2");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " started");
                friend1.methodFirst(friend2);
                System.out.println("1. friend2 call to friend1");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " started");
                friend2.methodFirst(friend1);
                System.out.println("2. friend2 waiting friend1 recall");
            }
        }).start();
    }
}