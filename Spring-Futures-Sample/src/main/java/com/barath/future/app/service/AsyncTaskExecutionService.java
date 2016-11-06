package com.barath.future.app.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class AsyncTaskExecutionService {
	
	
	
	private static final Logger logger=LoggerFactory.getLogger(AsyncTaskExecutionService.class);	
	
	@Async
	public ListenableFuture<Object>  asyncTask(String name) {
		logger.info("asyncTask getting executed ");
		
			try {
				if(name.equalsIgnoreCase("ARIVU")){
					throw new Exception("Simplye throwing the exception when counter is 1");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
				return null;
				
			}
		
		return new AsyncResult<Object>(name);
	}

}
