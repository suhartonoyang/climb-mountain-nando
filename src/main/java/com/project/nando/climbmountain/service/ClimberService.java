package com.project.nando.climbmountain.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.nando.climbmountain.model.Climber;
import com.project.nando.climbmountain.repo.ClimberRepository;

@Service
public class ClimberService {

	@Autowired
	private ClimberRepository climberRepository;

	public List<Climber> getClimberAll() {
		return StreamSupport.stream(climberRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}
	
	public Climber saveClimber(Climber climber) {
		return climberRepository.save(climber);
	}
}
