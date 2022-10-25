package sync;

import java.util.concurrent.atomic.AtomicInteger;

import static sync.SynThread.noSynNum;
import static sync.SynThread.synNum;

public class SynchronizedTest {
    public static void main(String[] args) {

        SynThread synThread = new SynThread();
        Thread thread = new Thread(synThread);
        thread.start();
        Thread thread2 = new Thread(synThread);
        thread2.start();
        try {
            thread.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("synNum ="+synNum);
        System.out.println("synNum ="+noSynNum);

    }
}
class SynThread implements Runnable{

    static  int noSynNum = 0;
    static  int synNum = 0;
    @Override
    public void run() {
        for (int i = 0;i < 100000;i++){
            synchronized (this){
                synNum++;
            }
            noSynNum++;
        }

    }
}
