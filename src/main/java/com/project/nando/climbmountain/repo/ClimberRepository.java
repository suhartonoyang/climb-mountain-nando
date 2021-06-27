package com.project.nando.climbmountain.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.nando.climbmountain.model.Climber;

@Repository
public interface ClimberRepository extends CrudRepository<Climber, Integer>{

}
