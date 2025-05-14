package day7.스레드풀메니저;



import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

//dill pugh -지연 초기화랑
public class ThreadPoolManager {

    private static class DeamonThreadFactory implements ThreadFactory{
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DeamonThreadFactory(String poolName){
            this.group = Thread.currentThread().getThreadGroup();
            this.namePrefix = "pool-" + poolName + "-thread-";

        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);

            //데몬 스레드 설정
            if(!t.isDaemon()){
                t.setDaemon(true);
            }

            if (t.getPriority() != Thread.NORM_PRIORITY){
                t.setPriority(Thread.NORM_PRIORITY);
            }

            return t;
        }
    }

    //고정 크기 스레드 풀(cpu 직압적 작업용)
    private final ExecutorService cpuBoundExecutor;

    //가변 크기 스레드 풀
    private final ExecutorService ioBoundExecutor;

    private final ScheduledExecutorService scheduledExecutor;

    private static class InstanceHolder{
        private static final ThreadPoolManager INSTANCE = new ThreadPoolManager();
    }

    private ThreadPoolManager(){
       //1.cpu코어수 기법으로 고정 크기 스레드 생성
        int cpuCore = Runtime.getRuntime().availableProcessors();
        this.cpuBoundExecutor = Executors.newFixedThreadPool(
                cpuCore,
                new DeamonThreadFactory("cpu")
        );
        //2.가변 크기 스레드 생성
        this.ioBoundExecutor = Executors.newFixedThreadPool(
                cpuCore * 2,
                new DeamonThreadFactory("io")
        );
        //3.스케줄러 생성
        this.scheduledExecutor = Executors.newScheduledThreadPool(
               1,
                new DeamonThreadFactory("scheduled")
        );

    }
    public static ThreadPoolManager getInstance(){
        return InstanceHolder.INSTANCE;
    }

    public void execute(Runnable task, boolean isCpuBound){
        if (isCpuBound){
            cpuBoundExecutor.execute(task);
        }else {
            ioBoundExecutor.execute(task);
        }
        cpuBoundExecutor.execute(task);
    }

    public void scheduleAtFixedRate(Runnable task,long initialDelay, long period){
        scheduledExecutor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.MILLISECONDS);
    }

    public void shutdown(){
        cpuBoundExecutor.shutdown();
        ioBoundExecutor.shutdown();
        scheduledExecutor.shutdown();
    }



}
