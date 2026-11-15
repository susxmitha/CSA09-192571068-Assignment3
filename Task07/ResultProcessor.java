import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
public class ResultProcessor {
    record Result(String file,boolean success,String detail){}
    private static Path prepareDataset(int count)throws IOException{
        Path dir=Paths.get("input");Files.createDirectories(dir);
        for(int i=0;i<count;i++){Path f=dir.resolve(String.format("script-%05d.txt",i));
            if(i%100==0)Files.deleteIfExists(f);
            else if(i%100==1)Files.writeString(f,"CORRUPT_SCORE_DATA");
            else Files.writeString(f,"student="+i+"\\nscore="+(i%101)+"\\n");
        }return dir;
    }
    private static Callable<Result> task(Path f){return ()->{try{
        List<String> lines=Files.readAllLines(f);
        if(lines.size()<2||!lines.get(0).startsWith("student=")||!lines.get(1).startsWith("score="))
            throw new IllegalArgumentException("Invalid score-file format");
        int score=Integer.parseInt(lines.get(1).substring(6));
        if(score<0||score>100)throw new IllegalArgumentException("Score out of range");
        return new Result(f.getFileName().toString(),true,"score="+score);
    }catch(IOException|RuntimeException e){return new Result(f.getFileName().toString(),false,e.getClass().getSimpleName()+": "+e.getMessage());}};}
    private static void runPool(Path dir,int count,int pool)throws Exception{
        ExecutorService ex=Executors.newFixedThreadPool(pool);ConcurrentLinkedQueue<Result> results=new ConcurrentLinkedQueue<>();
        List<Future<Result>> futures=new ArrayList<>();long start=System.nanoTime();
        try{for(int i=0;i<count;i++){Path f=dir.resolve(String.format("script-%05d.txt",i));futures.add(ex.submit(task(f)));}
            for(Future<Result> future:futures)results.add(future.get());
        }finally{ex.shutdown();if(!ex.awaitTermination(1,TimeUnit.MINUTES))ex.shutdownNow();}
        long elapsed=System.nanoTime()-start;long ok=results.stream().filter(Result::success).count();long fail=results.size()-ok;
        System.out.printf("Pool=%d, Files=%d, Success=%d, Failure=%d, Time_ms=%.3f%n",pool,results.size(),ok,fail,elapsed/1_000_000.0);
        if(ok+fail!=count)throw new IllegalStateException("Incomplete summary");
    }
    public static void main(String[] args)throws Exception{
        int count=args.length>0?Integer.parseInt(args[0]):10000;Path dir=prepareDataset(count);
        System.out.println("Thread-pool benchmark for fault-tolerant result processing");
        System.out.println("Expected injected failures: approximately 2% (missing and corrupt files)");
        for(int pool:new int[]{1,2,4,8})runPool(dir,count,pool);
    }
}