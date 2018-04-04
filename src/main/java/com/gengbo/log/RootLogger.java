package com.gengbo.log;

import com.gengbo.log.suba.SubALogger;
import com.gengbo.log.subb.SubBLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 本例主要演示父子logger的场景，子logger延迟打印日志，同时向父logger打印日志时，出现时间乱序的问题
 * 主要点在于：
 *     1 日志时间loggingevent初始化时时间戳已经初始化
 *     2 日志打印时显示的时间戳就是loggingevent的时间戳
 *     3 多个子logger向父logger打印日志时，相当于多线程写，有个别线程会阻塞
 * Created by gengbo on 2018/3/30.
 */
public class RootLogger {
    private static Logger logger = LoggerFactory.getLogger(RootLogger.class);

    public void writeRootLogger() {
        logger.info("writeRootLogger");
    }

    public static void main(String[] args) {
        final RootLogger rootLogger = new RootLogger();
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i =0; i < 10; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep( new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.submit(new Runnable() {
                public void run() {
//                    rootLogger.caseA();
                    rootLogger.caseB();
                }
            });

            executorService.submit(new Runnable() {
                public void run() {
                    rootLogger.writeRootLogger();
                }
            });
        }

        try {
            executorService.awaitTermination(15, TimeUnit.SECONDS);
            executorService.shutdown();
            System.out.println("shutdown....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void caseA() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date time = Calendar.getInstance().getTime();
//        System.out.println(Thread.currentThread().getName() + "时间戳" + sdf.format(time));
        SubALogger subALogger = new SubALogger();
        SubBLogger subBLogger = new SubBLogger();
        subALogger.writeSubALogger(time);
        subBLogger.writeSubBLogger(time);
    }

    public void caseB() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date time = Calendar.getInstance().getTime();
//        System.out.println(Thread.currentThread().getName() + "时间戳" + sdf.format(time));
        SubALogger subALogger = new SubALogger();
        SubBLogger subBLogger = new SubBLogger();
        subBLogger.writeSubBLogger(time);
        subALogger.writeSubALogger(time);
    }

}
