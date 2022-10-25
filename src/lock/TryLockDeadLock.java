package lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述 用 tryLock 来避免死锁
 */
public class TryLockDeadLock implements Runnable{

    int flag = 1;
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();
    @Override
    public void run() {

        for (int i = 0;i < 5;i++){
            if(flag == 1){
                try{
                    if(lock1.tryLock(800, TimeUnit.MILLISECONDS)){

                        try {
                            System.out.println("线程1 获取到了锁1");
                            Thread.sleep(new Random().nextInt(1000));
                            if(lock2.tryLock(800,TimeUnit.MILLISECONDS)){
                                try {
                                    System.out.println("线程1 获取到了锁2");
                                    System.out.println("线程1 获取到了两把锁");

                                }finally {
                                    lock2.unlock();
                                }
                            }else {
                                System.out.println("线程1 主动放弃锁2");
                            }
                        }finally {
                            lock1.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    }else{
                        System.out.println("线程1获取锁失败，已重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else if(flag ==0){
                try{

                if(lock2.tryLock(3000, TimeUnit.MILLISECONDS)){

                    try {
                        System.out.println("线程2 获取到了锁2");
                        Thread.sleep(new Random().nextInt(1000));
                        if(lock1.tryLock(800,TimeUnit.MILLISECONDS)){
                            try {
                                System.out.println("线程2 获取到了锁1");
                                System.out.println("线程2 获取到了两把锁");

                            }finally {
                                lock1.unlock();
                            }
                        }else{
                            System.out.println("线程2 主动放弃锁1");
                        }
                    }finally {
                        lock2.unlock();
                        Thread.sleep(new Random().nextInt(1000));
                    }
                }else{
                    System.out.println("线程2获取锁失败，已重试");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            }
        }

    }

    public static void main(String[] args) {
        TryLockDeadLock lock = new TryLockDeadLock();
        lock.flag = 1;
        TryLockDeadLock lock2 = new TryLockDeadLock();
        lock2.flag =0;
        Thread t1 = new Thread(lock);
        t1.start();
        Thread t2 = new Thread(lock2);
        t2.start();
    }
}
