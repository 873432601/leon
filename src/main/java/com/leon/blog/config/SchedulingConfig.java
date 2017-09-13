package com.leon.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * @Description
 * @Author 蔡学亮(xueliang.cai@mljr.com)
 * @Date 2017年09月13日 11:31
 */
@Configuration
public class SchedulingConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        // add job
    /*    scheduledTaskRegistrar.addFixedRateTask(new IntervalTask(() -> System.out.println(
                "Job @ fixed rate " + new Date() + ", Thread name is " + Thread.currentThread().getName()), 1000,0));
    */}

    //配置定时任务线程池
    @Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("poolScheduler");
        scheduler.setPoolSize(10);
        return scheduler;
    }
}
