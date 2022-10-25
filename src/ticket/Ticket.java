package ticket;

/**
 * 同步函数用的是哪一个锁呢？
 * 函数需要被对象调用，那么函数都有一个所属对象引用，就是this
 * 所以同步函数使用的锁是this
 * 通过该程序进行验证
 * 使用两个线程买票
 * 一个在同步代码块中
 * 一个线程在同步函数中
 * 都在执行买票动作
 *
 */
public class Ticket implements Runnable {
    private static int ticket = 100;

    private Object object = new Object();

    boolean flag = true;


    @Override
    public void run() {

        if(flag) {
            while (true) {
                synchronized (Ticket.class) {
                    if (ticket > 0) {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + "...code " + ticket--);
                        //ticket = ticket -1;
                    }
                }
            }
        }else {
            while (true){
                printTicket();
            }
        }

    }

    private static synchronized void printTicket(){
        if (ticket > 0) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "...show" + ticket--);
            //ticket = ticket -1;
        }
    }
}
