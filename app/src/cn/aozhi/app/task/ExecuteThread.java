package cn.aozhi.app.task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ExecuteThread {
	private static Executor pool = Executors.newCachedThreadPool();
    private BlockingQueue<Runnable> storageQueue = new LinkedBlockingQueue<Runnable>(5);
	public static void execute(Runnable runnable){
		 pool.execute(runnable);
		 System.out.println("ccccccccccccccccccccccccccccccccccc");
	}
}
