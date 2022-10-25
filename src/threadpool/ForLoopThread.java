package threadpool;

import java.util.concurrent.Executors;

public class ForLoopThread {
    public static void main(String[] args) {
        for (int i = 0;i <10;i++){
            Thread thread = new Thread(new Task());
            thread.start();
        }
        Executors.newFixedThreadPool(4);


    }

    static class Task implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+":执行了任务");
        }
    }
}
