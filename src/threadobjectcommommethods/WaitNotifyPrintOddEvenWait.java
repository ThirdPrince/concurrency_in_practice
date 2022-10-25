package threadobjectcommommethods;

/**
 * 两个线程交替打印0-100的 奇数偶数 用wait 和notify
 *
 */
public class WaitNotifyPrintOddEvenWait {
    //1,拿到锁 我们就打印
    //2,打印完,唤醒其他线程，自己就休眠

    private static int count = 0;

    private static Object lock = new Object();

    static class TurningRunner implements Runnable{

        @Override
        public void run() {

            while (count <= 200){
                synchronized (lock){
                    System.out.println(Thread.currentThread().getName()+":"+count++);
                    lock.notify();
                    if(count <= 200){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }




    public static void main(String[] args) {
        new Thread(new TurningRunner(),"偶数").start();
        new Thread(new TurningRunner(),"奇数").start();

    }
}
