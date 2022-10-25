package deadlock;

/**
 * 死锁示例
 */
public class Dead implements Runnable{
    private boolean flag= false;
    Dead(boolean flag){
        this.flag = flag;
    }
    @Override
    public void run() {
        if(flag){
            synchronized (MyLock.lockA){
                System.out.println("lockA");
                synchronized (MyLock.lockB){
                    System.out.println("lockB");
                }
            }
        }else {
            synchronized (MyLock.lockB){
                System.out.println("else lockB");
                synchronized (MyLock.lockA){
                    System.out.println("else lockA");
                }
            }
        }
    }
}
class MyLock{
   static Object lockA = new Object();
   static Object lockB = new Object();
}
