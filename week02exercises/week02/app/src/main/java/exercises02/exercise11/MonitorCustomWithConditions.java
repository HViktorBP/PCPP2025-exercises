package exercises02.exercise11;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorCustomWithConditions {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition canRead = lock.newCondition();
    private final Condition canWrite = lock.newCondition();

    private int readers = 0;
    private int writersWaiting = 0;
    private boolean writing = false;
    private boolean preferWriter = true;

    public void readLock() throws InterruptedException {
        lock.lock();
        try {
            while (writing || (preferWriter && writersWaiting > 0)) {
                canRead.await();
            }
            readers++;
        } finally {
            lock.unlock();
        }
    }

    public void readUnlock() {
        lock.lock();
        try {
            readers--;
            if (readers == 0) {
                preferWriter = true;
                canWrite.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public void writeLock() throws InterruptedException {
        lock.lock();
        try {
            writersWaiting++;
            while (writing || readers > 0) {
                canWrite.await();
            }
            writersWaiting--;
            writing = true;
        } finally {
            lock.unlock();
        }
    }

    public void writeUnlock() {
        lock.lock();
        try {
            writing = false;
            preferWriter = false;
            if (writersWaiting > 0) {
                canWrite.signalAll();
            }
            canRead.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
