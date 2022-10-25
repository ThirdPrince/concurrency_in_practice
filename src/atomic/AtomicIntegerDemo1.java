package atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述 演示AtomicInteger  对比非原子类的线程安全问题，使用了原子类后，不需要加锁，也可以保证线程安全
 */
public class AtomicIntegerDemo1  implements Runnable{
    private static final AtomicInteger atomicInteger = new AtomicInteger();
    public void incrementAtomic(){
        atomicInteger.incrementAndGet();
    }
    private static volatile int basicCount = 0;
    public void incrementBasic(){
        basicCount++;
    }

    @Override
    public void run() {
        for (int i = 0;i < 10000;i++){
            incrementAtomic();
            incrementBasic();
        }
    }

    public static void main(String[] args) {
        AtomicIntegerDemo1 atomicIntegerDemo1 = new AtomicIntegerDemo1();
        Thread t1 =  new Thread(atomicIntegerDemo1);
        Thread t2 =  new Thread(atomicIntegerDemo1);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main atomic ="+atomicInteger.get());
        System.out.println("main basic  ="+basicCount);


    }
}
