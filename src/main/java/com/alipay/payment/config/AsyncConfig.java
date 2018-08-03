package com.alipay.payment.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    public static final String TASK_EXECUTOR_CONTROLLER = "controllerTaskExecutor";

    @Bean(name = TASK_EXECUTOR_CONTROLLER)
    public Executor getControllerAsyncExecutor() {
        return ForkJoinPool.commonPool();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

}
