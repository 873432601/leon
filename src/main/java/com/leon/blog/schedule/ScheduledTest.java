package com.leon.blog.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author 蔡学亮(xueliang.cai@mljr.com)
 * @Date 2017年09月13日 10:13
 */
@Component
public class ScheduledTest {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTest.class);
/*
    @Scheduled(cron = "0/1 * * * * ?")
    public void executeUploadTask() {

        // 间隔1分钟,执行工单上传任务
        Thread current = Thread.currentThread();
        System.out.println("定时任务1:" + current.getId());
        logger.info("ScheduledTest.executeUploadTask 定时任务1:" + current.getId() + ",name:" + current.getName());
    }
*/
}
