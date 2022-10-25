package threadshare;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized 版本生产者与消费者
 */
public class SynchronizedConsumerAndProducer {

    /**
     * 队列长度
     */
    private static final int MAX_LEN = 5;

    /**
     * 队列
     */
    private Queue<Integer> queue = new LinkedList<>();

    private Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    class Producer extends Thread {

        @Override
        public void run() {
            producer();
        }

        /**
         * 生产者
         */
        private void producer() {
            int count = 0;
            while (true) {
                lock.lock();
                try{
                    queue.add(count++);
                    condition.signalAll();
                    System.out.println("生产者生成了一条数据，当前队列的长度为：" + queue.size());
                    int random = (int)(Math.random()*100);
                    System.out.println("sleep :"+random);
                    try {
                        Thread.sleep(random);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }finally {
                    lock.unlock();
                }
//                synchronized (queue) {
//                    while (queue.size() == MAX_LEN) { //队列已满
//                        queue.notify();
//                        System.out.println("当前队列已满");
//                        try {
//                            queue.wait();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }

                }
            }
        }


    class Consumer extends Thread {

        @Override
        public void run() {
            consumer();
        }

        private void consumer() {
            while (true) {

                lock.lock();
                while (queue.size() == 0) {
                    condition.signalAll();
                    System.out.println("当前队列为空");
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Integer integer  = queue.poll();
                    System.out.println("当前队列产生数据：" + integer);
                    System.out.println("消费者消费一条任务，当前队列的长度为：" + queue.size());
                    Thread.sleep(800);
                    } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                        lock.unlock();
                    }
                }
            }



    }

    public static void main(String[] args) {
        SynchronizedConsumerAndProducer consumerAndProducer = new SynchronizedConsumerAndProducer();
        Producer producer = consumerAndProducer.new Producer();
        Consumer consumer = consumerAndProducer.new Consumer();
        producer.start();
        consumer.start();
    }
}
