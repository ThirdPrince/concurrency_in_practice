package threadobjectcommommethods;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  演示 sleep 不释放lock（lock 需要手动释放）
 */
public class SleepDontReleaseLock implements Runnable{

    private static final Lock lock = new ReentrantLock();

    @Override
    public void run() {
       lock.lock();
        System.out.println("线程："+Thread.currentThread().getName()+":获取到了锁");
        try {
            Thread.sleep(5000);
            System.out.println("线程："+Thread.currentThread().getName()+":已经苏醒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SleepDontReleaseLock sleepDontReleaseLock = new SleepDontReleaseLock();
        new Thread(sleepDontReleaseLock).start();
        new Thread(sleepDontReleaseLock).start();

    }
}
