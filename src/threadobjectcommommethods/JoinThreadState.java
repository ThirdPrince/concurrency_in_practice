package threadobjectcommommethods;

import javax.swing.plaf.synth.SynthRadioButtonMenuItemUI;

/**
 *  先join 在mainThread.getState()
 */
public class JoinThreadState {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("mainThread state:" + mainThread.getState());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread 已执行完");


    }
}
