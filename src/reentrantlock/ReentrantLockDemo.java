package reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    volatile static boolean isNotify = false;

    public static void main(String[] args) {
        ResourceTest resourceTest = new ResourceTest();
         Lock lock = new ReentrantLock();
         Condition condition = lock.newCondition();

         Thread threadB = new Thread(new Runnable() {
             @Override
             public void run() {
                 try {
                     Thread.sleep(200);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }

                 lock.lock();
                 try {
                     isNotify = true;
                     condition.signal();
                     System.out.println("唤醒其他线程");
                 }finally {
                     lock.unlock();
                 }
             }
         });
         threadB.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread threadA =  new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    if (!isNotify) {
                        condition.await();
                    }
                    System.out.println("必须等待其他线程先运行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        threadA.start();

    }
}

class ThreadA implements  Runnable{
    private ResourceTest resourceTest;
    public ThreadA(ResourceTest resourceTest){
        this.resourceTest = resourceTest;
    }
    @Override
    public void run() {
        resourceTest.set("A");
    }
}

class ThreadB implements  Runnable{
    private ResourceTest resourceTest;
    public ThreadB(ResourceTest resourceTest){
      this.resourceTest = resourceTest;
    }
    @Override
    public void run() {
        resourceTest.out();
    }
}

class ResourceTest {
    private String name;
    private int count = 1;
    private volatile boolean flag = false;
    private Lock lock = new ReentrantLock();

    private Condition producerCondition = lock.newCondition();
    private Condition consumerCondition = lock.newCondition();

    public void set(String name) {
        lock.lock();
        try {
            while (flag) {
                producerCondition.await();
            }
            this.name = name + ".." + count++;
            System.out.println("生产者 =" + Thread.currentThread().getName() + ":" + this.name);
            flag = true;
            consumerCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void out() {
        lock.lock();
        try {
            while (!flag) {
                consumerCondition.await();
            }
            System.out.println("消费者 = " + Thread.currentThread().getName() + "：" + name);
            flag = false;
            producerCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
