package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0;i< 1000;i++){
            executorService.submit(new CacheTask());
        }


    }
}

class CacheTask implements Runnable{

    @Override
    public void run() {
        System.out.println("Thread name:"+Thread.currentThread().getName());
    }
}
