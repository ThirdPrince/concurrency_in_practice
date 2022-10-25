package threadobjectcommommethods;

/**
 * 两个线程交替打印 0-100 的奇数偶数，用synchronized 关键字实现
 */
public class WaitNotifyPrintOddEvenSyn {
    private static int count;
    private static Object lock = new Object();

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100){
                    synchronized (lock){
                        if((count & 1)== 0){
                            System.out.println(Thread.currentThread().getName()+":"+count++);
                        }

                    }

                }
            }
        },"偶数").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100){
                    synchronized (lock){
                        if((count & 1)== 1){
                            System.out.println(Thread.currentThread().getName()+":"+count++);
                        }
                    }

                }
            }
        },"奇数").start();

    }
}
