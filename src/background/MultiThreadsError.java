package background;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  第一种 运行结果出错
 *  演示计数不准确（减少）找出具体出错的位置
 */
public class MultiThreadsError implements Runnable{
   static int index = 0;
   static AtomicInteger realIndex = new AtomicInteger();
   static AtomicInteger wrongCount = new AtomicInteger();
    static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    static volatile CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);

   final boolean[] marked = new boolean[10000000];
    @Override
    public void run() {

//        while (index < 10000){
//            index ++;
//        }

        for (int j = 0;j< 200000;j++){
            try {
                cyclicBarrier2.reset();
                cyclicBarrier1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            index ++;
            try {
                cyclicBarrier1.reset();
                cyclicBarrier2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            realIndex.incrementAndGet();
            synchronized (MultiThreadsError.class){
                if(marked[index]){
                    System.out.println("发生错误"+index);
                    wrongCount.incrementAndGet();
                }
                marked[index] = true;

            }

        }
    }

    public static void main(String[] args) {
        MultiThreadsError instance = new MultiThreadsError();
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main index = "+index);
        System.out.println("真正运行的次数:"+realIndex.get());
        System.out.println("错误的次数："+wrongCount.get());

    }
}
