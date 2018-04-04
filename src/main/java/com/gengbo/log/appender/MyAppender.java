package com.gengbo.log.appender;

import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.ConsoleAppender;

import java.util.concurrent.TimeUnit;

/**
 * Created by gengbo on 2018/3/30.
 */
public class MyAppender extends ConsoleAppender {
    protected void append(Object eventObject) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println("========= myappender========start");
        super.append(eventObject);
//        System.out.println("========= myappender========end");
    }
}
