package com.barath.future.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.barath.future.app.model.Team;

public interface TeamRepository extends CrudRepository<Team, String> {

}
