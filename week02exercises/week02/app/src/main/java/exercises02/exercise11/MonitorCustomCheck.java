package exercises02.exercise11;

import java.util.ArrayList;
import java.util.List;

public class MonitorCustomCheck {
    public static void main(String[] args) {
        List<Integer> sharedObject = new ArrayList<>() {{
            add(1);
        }};

//        MonitorCustom readWriteLockCustom = new MonitorCustom();
        MonitorCustomWithConditions readWriteLockCustom = new MonitorCustomWithConditions();

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    long id = Thread.currentThread().getId();
                    System.out.println("Thread " + id + " is reading");
                    readWriteLockCustom.readLock();
                    Integer value = sharedObject.get(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    readWriteLockCustom.readUnlock();
                }
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    long id = Thread.currentThread().getId();
                    System.out.println("Thread " + id + " is writing");
                    readWriteLockCustom.writeLock();
                    sharedObject.set(0, sharedObject.get(0) + 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    readWriteLockCustom.writeUnlock();
                }
            }).start();
        }
    }
}

