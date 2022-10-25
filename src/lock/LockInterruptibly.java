package lock;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptibly implements  Runnable {

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"尝试获取锁");
        try{
            lock.lockInterruptibly();
            try{
                System.out.println(Thread.currentThread().getName()+"获取到了锁");
                Thread.sleep(5000);
            }catch (InterruptedException e){
                System.out.println(Thread.currentThread().getName()+"睡眠期间被中断了");
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName()+"释放了锁");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(Thread.currentThread().getName()+"获得锁期间中断");
        }
    }

    public static void main(String[] args) {
        LockInterruptibly lockInterruptibly = new LockInterruptibly();
        Thread thread1 = new Thread(lockInterruptibly);
        thread1.start();
        Thread thread2 = new Thread(lockInterruptibly);
        thread2.start();
    }


}
