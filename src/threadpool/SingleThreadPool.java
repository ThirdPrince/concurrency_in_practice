package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0;i< 100;i++){
            executorService.submit(new TaskSingle());
        }

    }
}
class TaskSingle implements Runnable{

    @Override
    public void run() {
        System.out.println("Thread name :"+Thread.currentThread().getName());
    }
}
