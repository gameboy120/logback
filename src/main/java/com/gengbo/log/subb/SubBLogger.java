package com.gengbo.log.subb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by gengbo on 2018/3/30.
 */
public class SubBLogger {
    private static Logger logger = LoggerFactory.getLogger(SubBLogger.class);

    public void writeSubBLogger(Date time) {
        logger.info("writeSubBLogger:" + time);
    }
}
