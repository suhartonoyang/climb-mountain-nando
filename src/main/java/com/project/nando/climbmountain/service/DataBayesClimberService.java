package com.project.nando.climbmountain.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.nando.climbmountain.model.DataBayesClimber;
import com.project.nando.climbmountain.repo.DataBayesClimberRepository;

@Service
public class DataBayesClimberService {

	@Autowired
	private DataBayesClimberRepository dataBayesClimberRepository;

	public List<DataBayesClimber> getDataBayesClimberAll() {
		return StreamSupport.stream(dataBayesClimberRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

}
