package threadobjectcommommethods;

/**
 * 展示 wait 和 notify
 */
public class Wait {

    public static Object object = new Object();
    static class Thread1 extends Thread{
        @Override
        public void run() {
           synchronized (object){
               System.out.println(Thread.currentThread().getName()+" 开始执行了");
               try{
                   object.wait();
               }catch (InterruptedException e){
                   e.printStackTrace();
               }
               System.out.println("线程"+Thread.currentThread().getName()+"获取了锁");
           }
        }
    }
    static class Thread2 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                object.notify();
                System.out.println("线程:"+Thread.currentThread().getName()+"调用了notify");
            }
        }
    }

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        thread1.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread2 thread2 = new Thread2();
        thread2.start();
    }
}
