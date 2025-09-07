// For week 2
// sestoft@itu.dk * 2014-08-25
// raup@itu.dk * 2021-09-03
package exercises02;

public class TestMutableInteger {
    public static void main(String[] args) {
        final MutableInteger mi = new MutableInteger();
        Thread t = new Thread(() -> {
                while (mi.get() == 0)        // Loop while zero
                    {/* Do nothing*/ }
                System.out.println("I completed, mi = " + mi.get());
        });
        t.start();
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
        mi.set(42);
        System.out.println("mi set to 42, waiting for thread ...");
        try { t.join(); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("Thread t completed, and so does main");
    }
}

class MutableInteger {
    // WARNING: Not ready for usage by concurrent programs
    private volatile int value = 0; // 2.4. Added volatile for the task
    public /* synchronized */ void set(int value) {
        this.value = value;
    }
    // Added synchronized here to fix visibility
    // by using synchronized, unlock() is executed and CPU registers and low-level
    // cache are flushed to memory levels shared by all CPUs
    // 2.4. Commenting out synchronized for the task
    public /*synchronized*/ int get() {
        return value;
    }
}
