package stopthread;

public class StopThread implements Runnable{

    boolean flag = true;
    @Override
    public  void run() {
        while (flag && !Thread.currentThread().isInterrupted()){
//            try {
//                wait();
//            }catch (InterruptedException e) {
//                System.out.println(Thread.currentThread().getName()+"...Exception");
//                e.printStackTrace();
//            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"...run");
        }

    }
     void changeFlag(){
        flag = !flag;
     }
}
