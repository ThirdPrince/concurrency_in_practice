package stopthread;

public class StopThreadDemo {
    public static void main(String[] args) {
        StopThread stopThread = new StopThread();
        Thread thread1 =  new Thread(stopThread);
        Thread thread2 =  new Thread(stopThread);
        thread1.start();
        thread2.start();
//        try {
//            Thread.sleep(300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
       // stopThread.flag = false;
        int num = 0;
        while (true){
            if(num++ == 60){
               thread1.interrupt();
                break;
            }
            System.out.println("main");
        }

    }
}
