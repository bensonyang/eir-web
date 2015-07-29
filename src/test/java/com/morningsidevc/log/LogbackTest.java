package com.morningsidevc.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author float.lu
 */
public class LogbackTest {
    private static Logger LOG = LoggerFactory.getLogger(LogbackTest.class);

    @Test
    public void main(){
        LOG.debug("debug.......");
        LOG.info("info.......");
        LOG.error("error.......");
    }
}
