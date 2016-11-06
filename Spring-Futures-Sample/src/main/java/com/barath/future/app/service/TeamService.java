package com.barath.future.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.barath.future.app.model.Team;
import com.barath.future.app.repository.TeamRepository;


@Service
public class TeamService {
	
	private Map<String,Object> teamRepository;
	
	
	
	public TeamService(){
		this.teamRepository=new HashMap<String,Object>(5);
	}
	
	
	public void addTeam(Team team){
		System.out.println("ADD TEAM");
		if(team !=null){
			teamRepository.put(team.getTeamId(), team);
		}
	}
	
	public void addTeams(List<Team> teams){
		System.out.println("ADD TEAM");
		teams.forEach(team -> teamRepository.put(team.getTeamId(), team));
	}
	
	//@Async
	public Team getTeam(String teamId){
		System.out.println("getTeam TEAM");
		if(!StringUtils.isEmpty(teamId)){
			Team team= (Team) teamRepository.get(teamId);
			if(team !=null){
				System.out.println(team.toString());
				return team;
			}
		}
		
		return null;
	}
	
	public void updateTeam(Team team){
		System.out.println("updateTeam TEAM");
		if(team !=null){
			teamRepository.put(team.getTeamId(), team);
		}
	}
	
	public void deleteTeam(Team team){
		System.out.println("deleteTeam");
		if(team != null ){
			teamRepository.remove(team.getTeamId());
		}
	}
	
	public void deleteTeam(String teamId){
		System.out.println("deleteTeam TEAM");
		Team team=getTeam(teamId);
		if(team !=null){
			 teamRepository.remove(team.getTeamId());
		}
		
	}
	
	public List<Team> getAllTeams() throws InterruptedException{
		System.out.println("get ALL  TEAM");
		Thread.sleep(2000);
		List<Team> teams= teamRepository.entrySet().stream().map(x->(Team)x.getValue()).collect(Collectors.toList());
		 teams.forEach(System.out::println);
		 return teams;
	}
	
	

}
