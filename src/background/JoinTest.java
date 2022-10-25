package background;

public class JoinTest {
    public static void main(String[] args) {
        JoinThread joinThread = new JoinThread();
        Thread t1 = new Thread(joinThread);
        Thread t2 = new Thread(joinThread);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //joinThread.start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //joinThread.mainWait();
//                System.out.println("new Thread");
//            }
//        }).start();
//        joinThread.mainWait();

        System.out.println("main");



    }

}
