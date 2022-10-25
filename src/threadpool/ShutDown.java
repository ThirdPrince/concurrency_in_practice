package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 演示关闭线程池
 */
public class ShutDown {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i <1000;i++){
            executorService.execute(new ShutDownTask());
        }
        System.out.println(executorService.isShutdown());
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdownNow();
        System.out.println(executorService.isShutdown());
        try {
            executorService.awaitTermination(7L, TimeUnit.SECONDS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }


    }
}
class ShutDownTask implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("被中断了");
        }
        System.out.println("Thread name :"+Thread.currentThread().getName());
    }
}
