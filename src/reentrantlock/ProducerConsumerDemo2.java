package reentrantlock;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerDemo2 {

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

class Producer implements Runnable {

    private Resource resource;

    public Producer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            resource.set("+商品+");
        }
    }


}

class Consumer implements Runnable {

    private Resource resource;

    public Consumer(Resource consumer) {
        this.resource = consumer;
    }

    @Override
    public void run() {
        while (true) {
            resource.out();
        }
    }
}

class Resource {
    private String name;
    private int count = 1;
    private TreeMap<Integer,String> map = new TreeMap<>();
    private volatile boolean flag = false;
    private Lock lock = new ReentrantLock();

    private Condition producerCondition = lock.newCondition();
    private Condition consumerCondition = lock.newCondition();

    public void set(String name) {
        lock.lock();
        try {
            while (flag) {
                producerCondition.await();
            }
            this.name = name + ".." + count++;
            map.put(count,name);
            System.out.println("生产者 =" + Thread.currentThread().getName() + ":" + this.name);
            flag = true;
            consumerCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void out() {
        lock.lock();
        try {
            while (!flag) {
                consumerCondition.await();
            }
            System.out.println("消费者 = " + Thread.currentThread().getName() + "：" + name);
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<Integer,String> entry = (Map.Entry<Integer, String>) iterator.next();
                //System.out.println("key = "+entry.getKey()+":value:"+entry.getValue());

            }
            Thread.sleep(2000);
            flag = false;
            producerCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}




