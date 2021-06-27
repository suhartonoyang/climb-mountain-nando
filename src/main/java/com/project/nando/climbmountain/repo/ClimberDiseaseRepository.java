package com.project.nando.climbmountain.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.nando.climbmountain.model.Climber;
import com.project.nando.climbmountain.model.ClimberDisease;

@Repository
public interface ClimberDiseaseRepository extends CrudRepository<ClimberDisease, Integer>{

}
