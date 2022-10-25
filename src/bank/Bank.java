package bank;

/**
 * 需求
 * 银行有一个金库
 * 有两个储户 分别存300员 每次存100 存3次
 * 目的：该程序是否有安全问题，如果有 如何解决
 * 如何找问题
 * 1，明确哪些代码是多线程运行代码
 * 2，明白共享数据
 * 3，明确多线程运行代码中哪些语句是操作共享数据的
 */
public class Bank {
    private int sum ;
    public  void add(int n)
    {
        sum = sum +n;
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sum = "+sum);
    }
}
class Cus implements  Runnable{

    private Bank bank = new Bank();

    @Override
    public void run() {
        for (int x = 0; x<3;x++){
            bank.add(100);
        }
    }
}
