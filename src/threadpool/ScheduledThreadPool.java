package threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        for (int i = 0;i< 1000;i++){
            scheduledExecutorService.schedule(new ScheduleTask(),5, TimeUnit.SECONDS);
        }


    }
}
class ScheduleTask implements Runnable{

    @Override
    public void run() {
        System.out.println("Thread name :"+Thread.currentThread().getName());
    }
}
