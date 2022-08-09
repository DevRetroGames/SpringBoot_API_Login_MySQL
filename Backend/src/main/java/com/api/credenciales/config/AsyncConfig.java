package com.api.credenciales.config;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.api.credenciales.exceptions.AsyncExceptionHandler;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
  
  
  private static final Integer MINIMUM_CORE = 1 ;
  
  
  @Autowired
  private AsyncExceptionHandler asyncExceptionHandler ;
	
  
  
	@Bean( name = "asyncExecutor" )
	public Executor asyncExecutor() {
		
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor() ;
		
		executor.setCorePoolSize( this.numberMinCore() ) ;
		executor.setMaxPoolSize( this.numberMaxCore() ) ;
		executor.setQueueCapacity( this.totalNumber() ) ;
		executor.setThreadNamePrefix( "AsyncThread-" ) ;
		executor.initialize() ;
		
		return executor ;
		
	}
	
	
	
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
	    return this.asyncExceptionHandler ;
	}
	
	
	
	private int numberMinCore() {
		return MINIMUM_CORE ;
	}
	
	
	
	private int numberMaxCore() {
		return Runtime.getRuntime().availableProcessors() / 2 ;
	}
	
	
	
	private int totalNumber() {
		return Runtime.getRuntime().availableProcessors() - 1 ;
	}
	
	
}