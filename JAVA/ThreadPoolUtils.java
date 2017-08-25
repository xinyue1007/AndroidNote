
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.RejectedExecutionException;
//https://github.com/xinyue1007/AndroidNote.git
public class ThreadPoolUtils {
    private static int mCorePoolSize=5;
    private static int mMaximumPoolSize=10;
    private static int mKeepAliveTime=10;
    private static TimeUnit mUnit=TimeUnit.SECONDS;
    static ArrayBlockingQueue mWorkQueue =new ArrayBlockingQueue<Runnable>(6) ; 

    public static void main(String[] args) { 
    	ThreadPoolExecutor mThreadPoolExecutor=new ThreadPoolExecutor(mCorePoolSize, mMaximumPoolSize, mKeepAliveTime, mUnit, mWorkQueue);
        for(int i=0;i<50;i++){
        	 MyTask myTask = new MyTask(i);
                    	 try{
            mThreadPoolExecutor.execute(myTask);
            }catch(RejectedExecutionException e){
            	 System.out.println("RejectedExecutionException: "+e.getMessage().toString());
            }
            System.out.println("------------------第"+i+"次--------start--------");
            System.out.println("线程池中线程数目："+mThreadPoolExecutor.getPoolSize()+"，队列中等待执行的任务数目："+
            		mThreadPoolExecutor.getQueue().size()+"，已执行玩别的任务数目："+mThreadPoolExecutor.getCompletedTaskCount());
            System.out.println("------------------第"+i+"次---------end-------");
            System.out.println("\n");
        }
        mThreadPoolExecutor.shutdown();
    }


static class MyTask implements Runnable {
    private int taskNum;
     
    public MyTask(int num) {
        this.taskNum = num;
    }
     
    @Override
    public void run() {
        System.out.println("正在执行task "+taskNum);
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task "+taskNum+"执行完毕");
    }
}
}

/*private void Exe(){
ThreadPoolExecutor mThreadPoolExecutor=new ThreadPoolExecutor(mCorePoolSize, mMaximumPoolSize, mKeepAliveTime, mUnit, mWorkQueue);
for(int i=0;i<15;i++){
    MyTask myTask = new MyTask(i);
    mThreadPoolExecutor.execute(myTask);
    System.out.println("------------------第"+i+"次--------start--------");
    System.out.println("线程池中线程数目："+mThreadPoolExecutor.getPoolSize()+"，队列中等待执行的任务数目："+
    		mThreadPoolExecutor.getQueue().size()+"，已执行玩别的任务数目："+mThreadPoolExecutor.getCompletedTaskCount());
    System.out.println("------------------第"+i+"次---------end-------");
    System.out.println("\n");
}
mThreadPoolExecutor.shutdown();

}*/
