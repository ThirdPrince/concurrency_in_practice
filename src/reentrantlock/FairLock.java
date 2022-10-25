package reentrantlock;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示公平 和不公平两种情况
 */
public class FairLock {


    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Job job = new Job(printQueue);
        Thread thread[] = new Thread[10];
        for (int i = 0;i <thread.length;i++){
            thread[i] = new Thread(job);
            thread[i].start();
        }
    }


}

class Job implements Runnable{
    PrintQueue printQueue;
    public Job(PrintQueue printQueue){
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+":开始打印");
        printQueue.printJob(new Object());
        System.out.println(Thread.currentThread().getName()+":开始完毕");
    }
}
class PrintQueue{

    private Lock queueLock = new ReentrantLock(true);

    public void printJob(Object document){
        queueLock.lock();
        try {
            int duration = new Random().nextInt(10)+1;
            System.out.println(Thread.currentThread().getName()+":正在打印需要:"+duration+"秒");
            Thread.sleep(duration*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }

        queueLock.lock();
        try {
            int duration = new Random().nextInt(10)+1;
            System.out.println(Thread.currentThread().getName()+":正在打印需要:"+duration+"秒");
            Thread.sleep(duration*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }
    }
}
