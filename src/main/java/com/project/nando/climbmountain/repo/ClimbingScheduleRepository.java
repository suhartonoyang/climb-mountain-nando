package com.project.nando.climbmountain.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.nando.climbmountain.model.ClimbingSchedule;

@Repository
public interface ClimbingScheduleRepository extends CrudRepository<ClimbingSchedule, Integer> {

}
