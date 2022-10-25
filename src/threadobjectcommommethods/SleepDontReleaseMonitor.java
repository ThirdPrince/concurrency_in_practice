package threadobjectcommommethods;

/**
 * 展示线程sleep 的时候不释放synchronized 的monitor 等sleep时间到了以后 正常结束后才释放锁
 */
public class SleepDontReleaseMonitor implements Runnable{
    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        System.out.println("线程"+Thread.currentThread().getName()+"获取到了monitor");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程"+Thread.currentThread().getName()+"退出了同步代码块");

    }

    public static void main(String[] args) {
        SleepDontReleaseMonitor sleepDontReleaseMonitor = new SleepDontReleaseMonitor();
        new Thread(sleepDontReleaseMonitor).start();
        new Thread(sleepDontReleaseMonitor).start();
    }
}
