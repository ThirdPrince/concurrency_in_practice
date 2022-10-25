package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class FixedThreadPoolOOM {
    private static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
        for (int i = 0;i< 10000;i++){
            executorService.submit(new SubThread());
        }
    }

}
class SubThread implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread name :"+Thread.currentThread().getName());
    }
}
