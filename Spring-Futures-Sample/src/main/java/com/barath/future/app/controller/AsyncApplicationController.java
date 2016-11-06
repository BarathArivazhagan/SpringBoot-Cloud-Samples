package com.barath.future.app.controller;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.barath.future.app.service.AsyncTaskService;

@RestController
public class AsyncApplicationController {
	
	private static final Logger logger=LoggerFactory.getLogger(AsyncApplicationController.class);	
	@Autowired
	private  AsyncTaskService asyncTaskService;
	
	@GetMapping("/callAsync")
	public void callAsync() throws InterruptedException, ExecutionException{
		System.out.println("AsyncApplicationController callAsync");
		
		asyncTaskService.callTasks();
	}
	
	@GetMapping("/callAsync/{times}")
	public void callAsyncTimes(@PathVariable int times) throws Exception{
		System.out.println("AsyncApplicationController callAsyncTimes");
		
		asyncTaskService.callTasks(times);
	}
	
}
