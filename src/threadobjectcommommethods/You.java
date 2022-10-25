package threadobjectcommommethods;

/**
 * 用户线程
 */
public class You implements Runnable{
    @Override
    public void run() {
        for (int i = 0;i< 10;i++){
            System.out.println("爱你一百年"+i);
        }
        System.out.println("一百年到了");

    }
}
