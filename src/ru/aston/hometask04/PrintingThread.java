package ru.aston.hometask04;

import java.util.concurrent.Semaphore;

public class PrintingThread implements Runnable{
    private final Semaphore acquireSemaphore; // на нём поток ждёт своей очереди
    private final Semaphore releaseSemaphore; // ему поток передаёт ход после печати
    private final String value;               // что печатать

    public PrintingThread(Semaphore acquireSemaphore, Semaphore releaseSemaphore, String value) {
        this.acquireSemaphore = acquireSemaphore;
        this.releaseSemaphore = releaseSemaphore;
        this.value = value;
    }

    @Override
    public void run() {
        while (true) {
            try {
                acquireSemaphore.acquire(); // ждём своей очереди
                System.out.println(value);
                releaseSemaphore.release(); // отдаём ход другому потоку
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    static void main(String[] args) {

        Semaphore semaphoreForOne = new Semaphore(1); // ход сразу у потока 1
        Semaphore semaphoreForTwo = new Semaphore(0); // поток 2 ждёт

        Thread thread1 = new Thread(
                new PrintingThread(semaphoreForOne, semaphoreForTwo, "1"),
                "Thread-1"
        );

        Thread thread2 = new Thread(
                new PrintingThread(semaphoreForTwo, semaphoreForOne, "2"),
                "Thread-2"
        );

        thread1.start();
        thread2.start();
    }
}
