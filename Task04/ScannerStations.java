import java.util.*;
import java.util.concurrent.CountDownLatch;

public class ScannerStations {
    static class Station extends Thread {
        private final int scripts, delay;
        Station(String name, int scripts, int delay) {
            super(name); this.scripts = scripts; this.delay = delay;
        }
        public void run() {
            try { Thread.sleep(delay); System.out.println(getName() + " scanned " + scripts + " scripts"); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
    }
    static List<String> runTrial(int trial, boolean priority) throws InterruptedException {
        List<String> order = Collections.synchronizedList(new ArrayList<>());
        Station[] stations = new Station[4];
        System.out.println("\\nTrial " + trial);
        for (int i=0;i<4;i++) {
            final String name = "Station-" + (i+1);
            final int delay = 20 + new Random().nextInt(80);
            stations[i] = new Station(name,25,delay) {
                public void run() {
                    try { Thread.sleep(delay); order.add(name);
                        System.out.println(name + " completed scanning 25 scripts"); }
                    catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                }
            };
            if (priority) {
                int p = (i==0 ? Thread.MIN_PRIORITY : i==2 ? Thread.MAX_PRIORITY : Thread.NORM_PRIORITY);
                stations[i].setPriority(p);
                System.out.println(name + " priority = " + p);
            }
        }
        for (Station s:stations) s.start();
        System.out.println("Station-1 alive after start: " + stations[0].isAlive());
        for (Station s:stations) s.join();
        System.out.println("Completion order: " + order);
        return order;
    }
    static void runnableDemo() throws InterruptedException {
        System.out.println("\\n========== RUNNABLE EXPERIMENT ==========");
        Thread[] ts = new Thread[4];
        for(int i=0;i<4;i++) {
            final int id=i+1;
            ts[i]=new Thread(() -> {
                try { Thread.sleep(100); System.out.println("Station-"+id+" (Runnable) scanned 25 scripts"); }
                catch(InterruptedException e){Thread.currentThread().interrupt();}
            });
            ts[i].start();
        }
        for(Thread t:ts)t.join();
        System.out.println("All Runnable stations completed.");
    }
    static void runVsStart() throws InterruptedException {
        System.out.println("\\n========== RUN() VS START() ==========");
        Station direct=new Station("Direct-Run",25,100);
        System.out.println("Calling run() directly...");
        direct.run();
        System.out.println("Direct run executed by: "+Thread.currentThread().getName());
        Station separate=new Station("Separate-Thread",25,100);
        System.out.println("\\nCalling start()...");
        separate.start(); separate.join();
        System.out.println("Separate thread execution completed.");
    }
    static void daemonDemo() throws InterruptedException {
        System.out.println("\\n========== DAEMON THREAD EXPERIMENT ==========");
        CountDownLatch started=new CountDownLatch(1);
        Thread daemon=new Thread(() -> {
            System.out.println("Daemon scanner started."); started.countDown();
            try { Thread.sleep(3000); System.out.println("Daemon scanner completed."); }
            catch(InterruptedException e){Thread.currentThread().interrupt();}
        },"Daemon-Scanner");
        daemon.setDaemon(true);
        System.out.println("Is daemon thread? "+daemon.isDaemon());
        daemon.start(); started.await();
        System.out.println("Main thread is ending without joining daemon.");
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println("============================================");
        System.out.println("PARALLEL ANSWER-SCRIPT SCANNER EXPERIMENT");
        System.out.println("============================================");
        System.out.println("\\n========== TEN EXECUTION TRIALS ==========");
        for(int i=1;i<=10;i++) runTrial(i,false);
        runnableDemo(); runVsStart();
        System.out.println("\\n========== THREAD PRIORITY EXPERIMENT ==========");
        runTrial(1,true);
        System.out.println("Note: Priority does not guarantee execution order.");
        daemonDemo();
    }
}