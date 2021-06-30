package com.project.nando.climbmountain.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.nando.climbmountain.model.DataBayesClimber;

@Repository
public interface DataBayesClimberRepository extends CrudRepository<DataBayesClimber, Integer> {

}
