package ticket;

public class ThisLockDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        Thread thread1 = new Thread(ticket);
        Thread thread2 = new Thread(ticket);
        thread1.start();
        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ticket.flag = false;
        thread2.start();
    }
}

