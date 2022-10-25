package reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程间通信：
 * 其实就是多个线程操作同一个资源
 * 但是操作的动作不同
 */
public class Res {
    String name;
    String sex;
    volatile boolean flag = false;
    Lock lock = new ReentrantLock();

}
