package threadobjectcommommethods;

/**
 * 守护线程
 */
public class God implements Runnable {
    @Override
    public void run() {
        while (true){
            System.out.println("你好好爱她，我来守护你哟");
        }

    }
}
