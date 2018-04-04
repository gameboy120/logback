package com.gengbo.log.suba;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by gengbo on 2018/3/30.
 */
public class SubALogger {
    private static Logger logger = LoggerFactory.getLogger(SubALogger.class);

    public void writeSubALogger(Date time) {
        logger.info("writeSubALogger:" + time);
    }
}
