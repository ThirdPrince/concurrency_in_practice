package threadobjectcommommethods;

/**
 * 3个线程 线程1 和线程2 首先被阻塞，线程3唤醒他们，notify notifyAll
 *
 */
public class WaitNotifyAll implements Runnable {

    private static final Object resourceA = new Object();


    @Override
    public void run() {
        synchronized (resourceA){
            System.out.println(Thread.currentThread().getName()+":get resourceA lock");
            try {
                System.out.println(Thread.currentThread().getName()+":waits to start ");
                resourceA.wait();
                System.out.println(Thread.currentThread().getName()+":waits to end ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public static void main(String[] args) {
        Runnable r = new WaitNotifyAll();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA){
                    resourceA.notifyAll();
                    System.out.println("Thread c notify");
                }
            }
        });
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread3.start();
    }
}
