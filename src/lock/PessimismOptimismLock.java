package lock;

import java.nio.file.AtomicMoveNotSupportedException;
import java.util.concurrent.atomic.AtomicInteger;

public class PessimismOptimismLock {

    public synchronized   void testMethod(){

    }

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
    }
}
