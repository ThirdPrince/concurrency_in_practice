package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述 Lock 不会像synchronzied 一样，异常的时候自动释放锁，所以最佳实践是 finally 中释放锁，以便保证异常的时候锁一定被释放
 */
public class MustUnLock {

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            //获取本锁保护的资源
            System.out.println(Thread.currentThread().getName()+"开始执行任务");
        }finally {
            lock.unlock();
        }
    }
}
