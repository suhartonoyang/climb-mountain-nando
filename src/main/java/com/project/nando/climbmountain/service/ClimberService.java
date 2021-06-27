package com.project.nando.climbmountain.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.nando.climbmountain.model.Climber;
import com.project.nando.climbmountain.model.ClimberDisease;
import com.project.nando.climbmountain.repo.ClimberDiseaseRepository;
import com.project.nando.climbmountain.repo.ClimberRepository;

@Service
public class ClimberService {

	@Autowired
	private ClimberRepository climberRepository;

	@Autowired
	private ClimberDiseaseRepository climberDiseaseRepository;

	public List<Climber> getClimberAll() {
		return StreamSupport.stream(climberRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	@Transactional
	public Climber saveClimber(Climber climber) {
		Climber newClimber = climberRepository.save(climber);
		climber.getClimberDiseases().stream().forEach(p -> {
			p.setClimber(newClimber);
			p = climberDiseaseRepository.save(p);
		});
		return climber;
	}
}
