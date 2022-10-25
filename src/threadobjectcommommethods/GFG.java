package threadobjectcommommethods;

/**
 * 输出打印 奇数 偶数
 */
public class GFG {

    int count = 1;
    static  int N ;

    public void printOddNumber(){
        synchronized (this) {
            while (count < N) {
                while (count % 2 == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName()+":"+count+" ");
                count++;
                notify();
            }
        }
    }

    public void printEvenNumber(){
        synchronized (this){
            while (count < N ) {
                while (count % 2 ==1){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName()+":"+count+" ");
                count++;
                notify();

            }
        }
    }

    public static void main(String[] args) {
        N = 10;
        GFG gfg = new GFG();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                gfg.printOddNumber();
            }
        },"奇数");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                gfg.printEvenNumber();
            }
        },"偶数");
        thread1.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}
