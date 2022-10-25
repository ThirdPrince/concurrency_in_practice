package atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 演示原子数组的使用方法
 */
public class AtomicArrayDemo {
    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(1000);
        Increment increment = new Increment(atomicIntegerArray);
        Decrement decrement = new Decrement(atomicIntegerArray);


        Thread[] threadsIncrement = new Thread[100];
        Thread[] threadsDecrement = new Thread[100];

        for (int i = 0;i<100;i++){
            threadsIncrement[i] = new Thread(increment);
            threadsDecrement[i] = new Thread(decrement);
            threadsIncrement[i].start();
            threadsDecrement[i].start();
        }
        for (int i = 0;i <100;i++){
            try {
                threadsIncrement[i].join();
                threadsDecrement[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        System.out.println("main");
        for (int i = 0;i <atomicIntegerArray.length();i++){
            if(atomicIntegerArray.get(i) != 0){
                System.out.println("发现了错误"+i);
            }
        }
        System.out.println("运行结束");

    }
}
class Decrement implements Runnable{

    private AtomicIntegerArray array;

    public Decrement(AtomicIntegerArray array){
        this.array = array;
    }

    @Override
    public void run() {

        for (int i =0;i < array.length();i++){
            array.getAndDecrement(i);
        }
    }
}

class Increment implements Runnable{

    private AtomicIntegerArray array;
    public Increment(AtomicIntegerArray array){
        this.array = array;
    }
    @Override
    public void run() {
        for (int i =0;i < array.length();i++){
            array.getAndIncrement(i);
        }
    }
}
