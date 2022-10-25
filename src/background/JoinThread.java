package background;

public class JoinThread extends Thread{
 Object object = new Object();
    @Override
    public void run() {
        for (int i = 0;i <10000;i++){
            System.out.println("i="+i);
        }
    }

    public synchronized void mainWait(){
        while (isAlive()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
