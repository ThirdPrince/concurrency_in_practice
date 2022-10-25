package stopthread;

/**
 * 描述 带有sleep 中断线程的写法
 */
public class RightWayStopWithSleep implements Runnable{
    @Override
    public void run() {
        int num = 0;
        while (!Thread.currentThread().isInterrupted() && num < 10000){
            System.out.println(num );
            num++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }


    }

    public static void main(String[] args) {


        Thread thread = new Thread(new RightWayStopWithSleep());
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();

    }

}
