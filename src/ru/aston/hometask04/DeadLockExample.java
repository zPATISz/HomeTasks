package ru.aston.hometask04;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockExample {

    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    static void main(String[] args) {
        DeadLockExample liveLock = new DeadLockExample();
        new Thread(liveLock::operation1, "T1").start();
        new Thread(liveLock::operation2, "T2").start();
    }

    public void operation1() {

        lock1.lock();
        System.out.println("lock1 acquired, waiting to acquire lock2.");
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        lock2.lock();
        System.out.println("lock2 acquired.");

        System.out.println("executing first operation.");

        lock2.unlock();
        lock1.unlock();
    }

    public void operation2() {

        lock2.lock();
        System.out.println("lock2 acquired, waiting to acquire lock1.");
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        lock1.lock();
        System.out.println("lock1 acquired.");

        System.out.println("executing second operation.");

        lock2.unlock();
        lock1.unlock();
    }
}
