package exercises02.exercise11;

public class MonitorCustom
{
    private int readers = 0;
    private int writersWaiting = 0;
    private boolean writing = false;
    private boolean preferWriter = true;

    public synchronized void readLock() throws InterruptedException {
        while (writing || (preferWriter && writersWaiting > 0)) {
            this.wait();
        }
        readers++;
    }

    public synchronized void writeLock() throws InterruptedException {
        writersWaiting++;
        try {
            while (writing || readers > 0) {
                this.wait();
            }
            writing = true;
        } finally {
            writersWaiting--;
        }
    }

    public synchronized void readUnlock() {
        readers--;
        if (readers == 0) {
            preferWriter = true;
            this.notifyAll();
        }
    }

    public synchronized void writeUnlock() {
        writing = false;
        preferWriter = false;
        this.notifyAll();
    }
}

