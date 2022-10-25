package reentrantlock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CinemaReadWrite {
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(true);
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
    private static void read(){
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"得到了读锁，正在读取");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            System.out.println("释放了读锁");
        }
    }

    private static void write(){
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"得到了写锁，正在写");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
            System.out.println("释放了写");
        }
    }

    public static void main(String[] args) {
        new Thread(()->write(),"thread-0").start();
        new Thread(()->read(),"thread-1").start();
        new Thread(()->read(),"thread-2").start();
        new Thread(()->write(),"thread-3").start();
        new Thread(()->write(),"thread-4").start();
        new Thread(()->read(),"thread-5").start();
    }

}
