package com.barath.future.app.service;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
public class DeferedTaskService {
	
	
	private static final Logger logger=LoggerFactory.getLogger(DeferedTaskService.class);
	
	public Object handleDeferredTask() throws InterruptedException{
		logger.info("handleDeferredTask ");
		DeferredResult<Object> deferredResult=callDeferredTask();
		Object result=null;
		if(deferredResult.hasResult()){
			Thread.sleep(10000);
			 result=deferredResult.getResult();
		}
		logger.info("RESULT IS "+result);
		return result ;
		
		
	}
	
	public DeferredResult<Object> callDeferredTask(){
		logger.info("callDeferredTask ");
		DeferredResult<Object> deferredResult=new DeferredResult<Object>(new Long(1));
		deferredResult.setResult(Arrays.asList("BARATH","PARI"));
		deferredResult.onTimeout(onTimeRunner);
		return deferredResult;
	}
	
	Runnable onTimeRunner=new Runnable() {
		
		@Override
		public void run() {
			logger.info("DEFERRED result is timed out ");
			System.out.println("DEFERRED result is timed out");
			
		}
	};

}
