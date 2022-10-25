package threadpool;

import java.sql.SQLOutput;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示 newFixedThreadPool
 */
public class FixedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService  = Executors.newFixedThreadPool(4);

        for (int i = 0;i < 1000;i++){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.submit(new Task());
        }

    }


}

 class Task implements Runnable{

     @Override
     public void run() {
         System.out.println("Thread name :"+Thread.currentThread().getName());
     }
 }
