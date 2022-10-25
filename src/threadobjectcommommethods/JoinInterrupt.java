package threadobjectcommommethods;

/**
 *  演示 join 期间被中断的效果
 */
public class JoinInterrupt {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mainThread.interrupt();
                    Thread.sleep(5000);
                    System.out.println("Thread1 finished");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(Thread.currentThread().getName()+"主线程中断了");
        }
        System.out.println("子线程运行完毕");




    }
}
