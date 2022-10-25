package stopthread;

public class RightWayStopWithSleepEveryLoop implements Runnable{

    @Override
    public void run() {
        int num = 0;
        while ( !Thread.currentThread().isInterrupted() && num < 10000){
            if(num % 10 == 0){
                System.out.println(num +"是10000的倍数");
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().isInterrupted();
                e.printStackTrace();
            }

            num++;
        }

    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RightWayStopWithSleepEveryLoop());
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupted();
    }
}
