package exercises01;

import java.util.concurrent.locks.ReentrantLock;

public class PrinterMain {
    public static void main(String[] args) {
        Printer printer = new Printer();
        ReentrantLock lock = new ReentrantLock();

        Thread thread1 = new PrinterThread(printer, lock);
        Thread thread2 = new PrinterThread(printer, lock);

        thread1.start();
        thread2.start();
    }
}

class PrinterThread extends Thread {
    Printer printer;
    ReentrantLock lock;

    public PrinterThread(Printer printer, ReentrantLock lock) {
        this.printer = printer;
        this.lock = lock;
    }

    public void run(){
        while(true){
            lock.lock();

            try {
                printer.print();
            } finally {
                lock.unlock();
            }
        }
    }
}
