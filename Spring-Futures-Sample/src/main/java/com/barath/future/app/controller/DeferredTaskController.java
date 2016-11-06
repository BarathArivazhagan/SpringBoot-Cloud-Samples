package com.barath.future.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barath.future.app.service.DeferedTaskService;

@RestController
public class DeferredTaskController {
	
	@Autowired
	private DeferedTaskService deferredTaskService;
	
	
	@GetMapping("/deferredTask")
	public Object handleDeferredCall(){
		return deferredTaskService.callDeferredTask();
	}

}
