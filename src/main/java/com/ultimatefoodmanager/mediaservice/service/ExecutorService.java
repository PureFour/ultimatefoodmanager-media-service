package com.ultimatefoodmanager.mediaservice.service;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;

@Service
@AllArgsConstructor
public class ExecutorService {

    @Bean(name = "MediaServiceAsyncJobHandler")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("MediaServiceAsyncJobHandler-");
        executor.initialize();
        return executor;
    }
}
