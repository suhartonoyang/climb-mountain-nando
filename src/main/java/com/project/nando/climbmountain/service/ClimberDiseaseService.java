package com.project.nando.climbmountain.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.nando.climbmountain.model.ClimberDisease;
import com.project.nando.climbmountain.model.ClimbingSchedule;
import com.project.nando.climbmountain.repo.ClimberDiseaseRepository;
import com.project.nando.climbmountain.repo.ClimbingScheduleRepository;

@Service
public class ClimberDiseaseService {

	@Autowired
	private ClimberDiseaseRepository climberDiseaseRepository;

	public ClimberDisease saveClimberDisease(ClimberDisease climberDisease) {
		return climberDiseaseRepository.save(climberDisease);
	}

}
