package com.gengbo.log.appender;

import ch.qos.logback.core.ConsoleAppender;

import java.util.concurrent.TimeUnit;

/**
 * Created by gengbo on 2018/3/30.
 */
public class MyConsoleAppender extends ConsoleAppender {
    protected void append(Object eventObject) {
//        System.out.println("========= consoleappender========start");
        super.append(eventObject);
//        System.out.println("========= consoleappender========end");
    }
}
