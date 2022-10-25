package stopthread;

/**
 * 描述
 * 最佳实践 catch 了Interrupted Exception 之后 优先选择：在方法签名中抛出异常
 * 那么在run() 就会强制try/catch
 */
public class RightWayStopThreadInProd2 implements Runnable{

    @Override
    public void run() {
        while (true && !Thread.currentThread().isInterrupted()){
            System.out.println("go");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                //System.out.println("保存日志");
                e.printStackTrace();
            }
        }
    }

    private void throwInMethod() throws InterruptedException {

        Thread.sleep(1000);


    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RightWayStopThreadInProd2());
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
