package com.fsj.concurrent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 线程池负责管理工作线程，包含一个等待执行的任务队列。线程池的任务队列是一个Runnable集合，
 * 工作线程负责从任务队列中取出并执行Runnable对象。
 * <p>
 * 1.6.4 线程池不允许使用 Executors 去创建,而是通过 ThreadPoolExecutor 的方式,
 * 这样 的处理方式让写的同学更加明确线程池的运行规则,规避资源耗尽的风险。
 * 说明:Executors 返回的线程池对象的弊端如下:
 * <p>
 * 1)FixedThreadPool 和 SingleThreadPool:
 * 允许的请求队列长度为 Integer.MAX_VALUE,可能会堆积大量的请求,从而导致 OOM。
 * 2)CachedThreadPool 和 ScheduledThreadPool:
 * 允许的创建线程数量为 Integer.MAX_VALUE,可能会创建大量的线程,从而导致 OOM。
 */
public class demo {

    private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };
    private static final ThreadLocal<DateFormat> df2 = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmm"));

    static void fixThreadPool() {
        //线程池的大小是5，因此首先会启动5个工作线程，其他任务将进行等待。一旦有任务结束，工作线程会从等待队列中挑选下一个任务并开始执行
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            Runnable worker = new WorkerThread("" + i);
            pool.execute(worker);
        }
        pool.shutdown();
        while (!pool.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }

    static void threadSafeSimpleDateFormat() {

    }

    public static void main(String[] args) {
        fixThreadPool();
    }
}
