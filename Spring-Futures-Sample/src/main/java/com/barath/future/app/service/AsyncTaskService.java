package com.barath.future.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.barath.future.app.controller.AsyncApplicationController;

@Service
public class AsyncTaskService {
	
	
	@Autowired
	private AsyncTaskExecutionService asyncTaskExecutionService;
	
	private static final Logger logger=LoggerFactory.getLogger(AsyncTaskService.class);	
	
	public void callTasks() {
		// TODO Auto-generated method stub
		
	}
	
	public void callTasks(int times) throws Exception{
		logger.info("callTasks getting executed ");
		List<ListenableFuture<Object>> results=new ArrayList<ListenableFuture<Object>>(times);
		List<String> names=Arrays.asList("BARATH","PARI","HEMA","RUKMANI","ARIVU","MAN1","MAN12","MAN13","MAN14","MAN15","MAN16","MAN1");
		for(int i=0;i<times;i++){
			results.add(asyncTasks(names.get(i)));
		}
		//Object value=Collections.collec
		
		results.stream().forEach( future -> {
			try {
				System.out.println("Result is "+future.get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	}
	
	public ListenableFuture<Object> asyncTasks(String name) throws Exception{
		logger.info("asyncTasks getting executed ");
		ListenableFuture<Object> futures=asyncTaskExecutionService.asyncTask(name);
		futures.addCallback(new AsyncTaskFutureCallback());
		return futures;
	}
	
	
	class AsyncTaskFutureCallback implements ListenableFutureCallback<Object>{

		@Override
		public void onSuccess(Object paramT) {
			logger.info("ON SUCCESS "+paramT);
			
		}

		@Override
		public void onFailure(Throwable paramThrowable) {
			logger.info("ON onFailure ");
			logger.error(paramThrowable.getMessage());
			
		}
		
	}
//	
//	Callable<Object> runAsyncTask=new Callable<Object>() {	
//
//		@Override
//		public Object call() throws Exception {			
//			return  asyncTask();
//		}
//	};


	
	

}
