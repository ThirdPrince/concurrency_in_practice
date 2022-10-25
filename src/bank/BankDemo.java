package bank;

public class BankDemo {
    public static void main(String[] args) {
        Cus cus = new Cus();
        Thread thread1 = new Thread(cus);
        Thread thread2 = new Thread(cus);
        thread1.start();
        thread2.start();
    }
}
