package reentrantlock;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示多线程预定电影院座位
 */
public class CinemaBook {

    private static ReentrantLock lock = new ReentrantLock();
    private static  void bookSet(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始预定座位");
            Thread.sleep(1000);
            System.out.println("完成预定座位");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> bookSet()).start();
        new Thread(() -> bookSet()).start();
        new Thread(() -> bookSet()).start();
        new Thread(() -> bookSet()).start();
    }
}
