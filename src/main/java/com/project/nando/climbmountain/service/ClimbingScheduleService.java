package com.project.nando.climbmountain.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.nando.climbmountain.model.ClimbingSchedule;
import com.project.nando.climbmountain.repo.ClimbingScheduleRepository;

@Service
public class ClimbingScheduleService {

	@Autowired
	private ClimbingScheduleRepository climbingScheduleRepository;

	public List<ClimbingSchedule> getClimbingScheduleAll() {
		return StreamSupport.stream(climbingScheduleRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}
	
	public ClimbingSchedule getClimbingScheduleById(int id) {
		return climbingScheduleRepository.findById(id).orElse(null);
	}

	@SuppressWarnings("deprecation")
	public List<ClimbingSchedule> getClimbingScheduleByMonthAndYear(int month, int year) {
		List<ClimbingSchedule> allSchedules = StreamSupport
				.stream(climbingScheduleRepository.findAll().spliterator(), false).collect(Collectors.toList());

		List<ClimbingSchedule> filterSchedules = allSchedules.stream().filter(p -> {
			boolean filter = p.getDate().getMonthValue() == month && p.getDate().getYear() == year;
			return filter;
		}).collect(Collectors.toList());

		return filterSchedules;
	}

}
