package threadshare;

public class ProducerConsumerDemo {

    public static void main(String[] args) {
        Resource resource = new Resource();
        Producer producer = new Producer(resource);
        Consumer consumer = new Consumer(resource);
        new Thread(producer).start();
        new Thread(producer).start();
        new Thread(producer).start();
        new Thread(consumer).start();

    }


}

class Producer implements Runnable{

    private Resource resource;

    public Producer(Resource resource){
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true){
            resource.set("+商品+");
        }
    }


}

class Consumer implements Runnable{

    private Resource resource;
    public Consumer(Resource consumer){
        this.resource = consumer;
    }

    @Override
    public void run() {
        while (true){
            resource.out();
        }
    }
}

class Resource{
    private String name;
    private int count = 1;
    private volatile boolean flag = false;
    public synchronized void set(String name){
        while (flag){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = name+".."+count++;
        System.out.println("生产者 ="+Thread.currentThread().getName()+":"+this.name);
        flag = true;
        notifyAll();
    }

    public synchronized void out(){
        while (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("消费者 = "+Thread.currentThread().getName()+"："+name);
        flag = false;
        notifyAll();
    }
}
