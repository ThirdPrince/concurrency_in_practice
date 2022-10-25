package threadobjectcommommethods;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *  每个1秒钟输出当前时间 被中断，观察
 *
 *  sleep 方法 可以让线程 进入waiting 状态,并且不占用CPU 资源 ，但是不释放锁
 *  直到规定时间在执行，休眠期间如果被中断,会抛出异常并清除中断状态
 *
 *
 */
public class SleepInterrupted implements Runnable{

    @Override
    public void run() {
        for (int i = 0;i<10;i++){
            if(Thread.currentThread().isInterrupted()){
                break;
            }
            System.out.println(new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("我被中断了");
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SleepInterrupted sleepInterrupted = new SleepInterrupted();
        Thread thread =  new Thread(sleepInterrupted);
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
