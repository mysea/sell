package com.sea.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
//@Slf4j
public class test1 {

    private final Logger logger = LoggerFactory.getLogger(test1.class);

    @Test
    public void test1(){
        String username = "sea";
        String password = "123456";
        logger.error("error...");
        logger.info("info...");
        logger.info("info...username: {}, password:{}.",username,password);
        logger.debug("debug...");
        logger.warn("warning...");



    }

}
