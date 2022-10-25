package stopthread;

/**
 * run 方法没有sleep 或wait 方法时 停止线程
 */
public class RightWayStopWithoutSleep implements Runnable {

    public static void main(String[] args) {

        Thread thread = new Thread(new RightWayStopWithoutSleep());
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    @Override
    public void run() {
        int num = 0 ;
        while(num <= Integer.MAX_VALUE / 2 && !Thread.currentThread().isInterrupted() ){
            if(num % 10000 == 0 ){
                System.out.println(num +"是10000的倍数");
            }
            num++;
        }
        System.out.println("任务结束了");

    }
}
