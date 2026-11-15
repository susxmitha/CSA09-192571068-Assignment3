import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SeatBooking {
    private final boolean[] booked;
    private final AtomicInteger successful = new AtomicInteger();
    private final AtomicInteger rejected = new AtomicInteger();
    private final AtomicInteger[] acceptedPerSeat;
    private SeatBooking(int seats) {
        booked = new boolean[seats]; acceptedPerSeat = new AtomicInteger[seats];
        for(int i=0;i<seats;i++) acceptedPerSeat[i]=new AtomicInteger();
    }
    public void bookWithoutSynchronization(int seat, CyclicBarrier barrier) {
        if(!booked[seat]) {
            try { barrier.await(); } catch(Exception e) { Thread.currentThread().interrupt(); return; }
            booked[seat]=true; successful.incrementAndGet(); acceptedPerSeat[seat].incrementAndGet();
        } else rejected.incrementAndGet();
    }
    public synchronized void bookSynchronizedMethod(int seat) {
        if(!booked[seat]) { booked[seat]=true; successful.incrementAndGet(); acceptedPerSeat[seat].incrementAndGet(); }
        else rejected.incrementAndGet();
    }
    public void bookSynchronizedBlock(int seat) {
        synchronized(this) {
            if(!booked[seat]) { booked[seat]=true; successful.incrementAndGet(); acceptedPerSeat[seat].incrementAndGet(); }
            else rejected.incrementAndGet();
        }
    }
    int doubleBookings() {
        int d=0; for(AtomicInteger a:acceptedPerSeat) if(a.get()>1) d+=a.get()-1; return d;
    }
    private static void raceTrials() throws InterruptedException {
        System.out.println("\\n======================================");
        System.out.println("UNSYNCHRONIZED BOOKING - 10 TRIALS");
        System.out.println("======================================");
        System.out.println("Trial,Successful,Rejected,DoubleBookings");
        for(int trial=1;trial<=10;trial++) {
            int seats=10, usersPerSeat=10; SeatBooking c=new SeatBooking(seats);
            CyclicBarrier[] barriers=new CyclicBarrier[seats];
            for(int i=0;i<seats;i++) barriers[i]=new CyclicBarrier(usersPerSeat);
            Thread[] users=new Thread[seats*usersPerSeat]; int k=0;
            for(int seat=0;seat<seats;seat++) for(int u=0;u<usersPerSeat;u++) {
                final int s=seat; users[k]=new Thread(() -> c.bookWithoutSynchronization(s,barriers[s])); users[k++].start();
            }
            for(Thread t:users)t.join();
            System.out.println(trial+","+c.successful.get()+","+c.rejected.get()+","+c.doubleBookings());
        }
    }
    private static void synchronizedTest(boolean block) throws InterruptedException {
        SeatBooking c=new SeatBooking(10); Thread[] users=new Thread[100];
        for(int i=0;i<100;i++) { final int seat=i%10;
            users[i]=new Thread(() -> { if(block)c.bookSynchronizedBlock(seat); else c.bookSynchronizedMethod(seat); });
            users[i].start();
        }
        for(Thread t:users)t.join();
        System.out.println("Successful bookings: "+c.successful.get());
        System.out.println("Rejected bookings: "+c.rejected.get());
        System.out.println("Double bookings: "+c.doubleBookings());
    }
    private static long benchmark(int mode,int n) throws InterruptedException {
        SeatBooking c=new SeatBooking(1000); ExecutorService ex=Executors.newFixedThreadPool(32);
        long start=System.nanoTime();
        for(int i=0;i<n;i++){ final int seat=i%1000;
            ex.execute(() -> { if(mode==1)c.bookSynchronizedMethod(seat); else c.bookSynchronizedBlock(seat); });
        }
        ex.shutdown(); ex.awaitTermination(2,TimeUnit.MINUTES);
        return System.nanoTime()-start;
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println("CINEMA SEAT BOOKING - CONCURRENCY ANALYSIS");
        raceTrials();
        System.out.println("\\n======================================");
        System.out.println("SYNCHRONIZED METHOD TEST");
        System.out.println("======================================");
        synchronizedTest(false);
        System.out.println("\\n======================================");
        System.out.println("SYNCHRONIZED BLOCK TEST");
        System.out.println("======================================");
        synchronizedTest(true);
        System.out.println("\\n======================================");
        System.out.println("10,000 BOOKING PERFORMANCE COMPARISON");
        System.out.println("======================================");
        System.out.println("Implementation,Bookings,Time_ms");
        benchmark(1,1000); benchmark(2,1000);
        System.out.println("Synchronized Method,10000,"+benchmark(1,10000)/1_000_000.0);
        System.out.println("Synchronized Block,10000,"+benchmark(2,10000)/1_000_000.0);
        System.out.println("\\n======================================");
        System.out.println("EXPERIMENT COMPLETED");
        System.out.println("======================================");
        System.out.println("Unsynchronized booking can cause duplicate sales.");
        System.out.println("Synchronized method and block protect the critical section.");
    }
}