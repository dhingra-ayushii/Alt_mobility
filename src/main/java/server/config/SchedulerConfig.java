package server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

public class SchedulerConfig {
	 @Bean
	    public ThreadPoolTaskScheduler taskScheduler() {
	        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
	        taskScheduler.setPoolSize(5);
	        taskScheduler.setThreadNamePrefix("scheduled-task-");
	        return taskScheduler;
	    }

}
