package reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class RecursionDemo {
    static ReentrantLock lock = new ReentrantLock();
    private static void accessResource(){
        lock.lock();
        System.out.println("已经对资源进行了处理");
        try {
            if(lock.getHoldCount() < 5){
                accessResource();
            }
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        accessResource();
    }
}
