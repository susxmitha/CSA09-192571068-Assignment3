import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
public class ConcurrencyProblems {
    static class TokenCounter {
        private final int[] buffer=new int[5]; private int count=0,in=0,out=0;
        public synchronized void put(int token)throws InterruptedException{
            while(count==buffer.length)wait(); buffer[in]=token; in=(in+1)%buffer.length; count++; notifyAll();
        }
        public synchronized int take()throws InterruptedException{
            while(count==0)wait(); int token=buffer[out]; out=(out+1)%buffer.length; count--; notifyAll(); return token;
        }
        synchronized int getCount(){return count;}
    }
    static class Wallet {
        private final int id; private int balance;
        Wallet(int id,int balance){this.id=id;this.balance=balance;}
        int getId(){return id;} int getBalance(){return balance;}
    }
    static void transfer(Wallet from,Wallet to,int amount){
        Wallet first=from.getId()<to.getId()?from:to, second=from.getId()<to.getId()?to:from;
        synchronized(first){synchronized(second){if(from.balance>=amount){from.balance-=amount;to.balance+=amount;}}}
    }
    public static void main(String[] args)throws InterruptedException{
        TokenCounter counter=new TokenCounter(); int[] collected=new int[2]; AtomicInteger max=new AtomicInteger();
        Thread cook=new Thread(()->{try{for(int t=1;t<=20;t++){counter.put(t);max.accumulateAndGet(counter.getCount(),Math::max);}}
            catch(InterruptedException e){Thread.currentThread().interrupt();}});
        Thread[] students=new Thread[2];
        for(int s=0;s<2;s++){final int id=s;students[s]=new Thread(()->{try{for(int k=0;k<10;k++)collected[id]+=counter.take();}
            catch(InterruptedException e){Thread.currentThread().interrupt();}});}
        cook.start();for(Thread t:students)t.start();cook.join();for(Thread t:students)t.join();
        System.out.println("Sum of tokens collected: "+(collected[0]+collected[1])+" (expected 210)");
        System.out.println("Maximum observed counter size: "+max.get());
        Wallet a=new Wallet(1,1000),b=new Wallet(2,1000);
        Thread t1=new Thread(()->{for(int i=0;i<1000;i++)transfer(a,b,1);});
        Thread t2=new Thread(()->{for(int i=0;i<1000;i++)transfer(b,a,1);});
        t1.start();t2.start();t1.join();t2.join();
        System.out.println("Wallet total after ordered-lock transfers: "+(a.getBalance()+b.getBalance()));
    }
}