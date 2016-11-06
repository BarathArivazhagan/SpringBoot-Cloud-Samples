package com.barath.future.app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barath.future.app.model.Team;
import com.barath.future.app.service.TeamService;

@RestController
public class ApplicationController {
	
	@Autowired
	private TeamService teamService;
	
	
	@GetMapping("/")
	public String home(){
		return "welcome home";
	}
	
	@GetMapping("/add5")
	public void add5Team(){
		
		
		teamService.addTeams(get5Teams());
		teamService.addTeams(getNext5Teams());
	}
	
	@GetMapping("/add10")
	public void add10Team(){
		//return "welcome home";
	}
	
	@GetMapping("/async")
	public void getTeamsAsync() throws InterruptedException, ExecutionException{
		
		CompletableFuture<List<Team>> teams=CompletableFuture.supplyAsync(() -> {
			try {
				return teamService.getAllTeams();
			} catch (InterruptedException e) {
				// TODO Auto-generated cat	ch block
				e.printStackTrace();
			}
			return null;
		});
		
		CompletableFuture<Team> team=CompletableFuture.supplyAsync(() -> {
			return teamService.getTeam("100");
		});
		
		
			List<Team> teamsFound=teams.get();
			//System.out.println("COMPLETEFUTURE ALL TEAM OUTPUT");
			//teamsFound.forEach(System.out::println);
		
			//System.out.println("COMPLETEFUTURE TEAM OUTPUT");
			Team teamFound=team.get();
			teamFound.toString();
		
		//teams.forEach(System.out::println);
		
	}
	
	@GetMapping("/sync")
	public void getTeamsSync() throws InterruptedException, ExecutionException{
		
		
		List<Team> teamsFound=teamService.getAllTeams();	
		
			Team teamFound=teamService.getTeam("100");
			
		
		
		
	}
	
	@GetMapping("/getAll")
	public List<Team> getAllTeams() throws InterruptedException{
		return teamService.getAllTeams();
	}
	
	
	
	private List<Team> get5Teams(){
		
		List<String> teamNames=Arrays.asList("INDIA","AUSTRALIA","SOUTHAFRICA","ENGLAND","SA");
		List<Team> teams=Arrays.asList(new Team("100",teamNames.get(0)),new Team("101",teamNames.get(1)),new Team("102",teamNames.get(2)),new Team("103",teamNames.get(3)),new Team("104",teamNames.get(4)));
		teams.forEach(System.out::println);	
		
		return teams;
		
	}
	
	
	private List<Team> getNext5Teams(){
		
		List<String> teamNames=Arrays.asList("WESTINDIES","ZIMBABWE","PAKISTAN","UAE","KENYA");
		List<Team> teams=Arrays.asList(new Team("105",teamNames.get(0)),new Team("106",teamNames.get(1)),new Team("107",teamNames.get(2)),new Team("108",teamNames.get(3)),new Team("109",teamNames.get(4)));
		teams.forEach(System.out::println);	
		
		return teams;
		
	}

}
