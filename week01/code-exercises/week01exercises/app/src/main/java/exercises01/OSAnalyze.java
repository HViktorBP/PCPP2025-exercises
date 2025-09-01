package exercises01;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.ProcessorCache;
import oshi.hardware.GlobalMemory;
import oshi.hardware.ComputerSystem;
import oshi.software.os.OperatingSystem;
import java.util.List;

public class OSAnalyze {
    public static void main(String[] args) {
        SystemInfo si = new SystemInfo();
        OperatingSystem os = si.getOperatingSystem();
        System.out.println("Operating System: " + os);
        ComputerSystem cs = si.getHardware().getComputerSystem();
        String manufacturer = cs.getManufacturer().toLowerCase();
        String model = cs.getModel().toLowerCase();

        boolean isEmulated = manufacturer.contains("vmware") || model.contains("vmware")
                || manufacturer.contains("virtualbox") || model.contains("virtualbox")
                || manufacturer.contains("qemu") || model.contains("kvm")
                || model.contains("virtual machine") ;

        System.out.println("Is it emulated? " + (isEmulated ? "Yes" : "No"));


        CentralProcessor cpu = si.getHardware().getProcessor();
        List<ProcessorCache> caches = si.getHardware().getProcessor().getProcessorCaches();
        GlobalMemory memory = si.getHardware().getMemory();

        System.out.println("--- Hardware Information ---");
        System.out.println("Number of Cores: " + cpu.getPhysicalProcessorCount());
        System.out.println("Size of Main Memory: " + (memory.getTotal()/(1024*1024*1024) ) + " GB");

        for (ProcessorCache cache : caches) {
            System.out.println("Cache Size: " + cache.getCacheSize() + " bytes");
            System.out.println("Cache Type: " + cache.getType());
            System.out.println("<----------->");
        }

        long start, spent = 0;
        long sum = 0;
        start = System.nanoTime();
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        spent += System.nanoTime() - start;

        System.out.println("---- Code Execution Time ----");
        System.out.println("Sum of 1 to 100 = " + sum);
        System.out.println("Execution Time: " + spent  +" nanoseconds");
    }
}
