package com.project.nando.climbmountain.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.nando.climbmountain.bean.ClimberClusterDtoList;
import com.project.nando.climbmountain.bean.Cluster;
import com.project.nando.climbmountain.bean.Response;
import com.project.nando.climbmountain.service.kmeans.EuclideanDistance;
import com.project.nando.climbmountain.service.kmeans.KMeansService;

@RestController
@RequestMapping("/api/k-means")
@CrossOrigin
public class KMeansController {

	@Autowired
	private KMeansService kMeansService;

	@GetMapping("/run")
	public ResponseEntity<Response> run(@RequestParam String bookingNumber, @RequestParam int totalClusters,
			@RequestParam int maxIterations) {
		Response resp = new Response();
		List<Cluster> clusters = kMeansService.run(bookingNumber, totalClusters, new EuclideanDistance(), maxIterations);

		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage(HttpStatus.OK.name());
//		clusters.stream().forEach(p -> {
//			System.out.println("------------------------------ CLUSTER " + p.getClusterNumber()
//					+ "-----------------------------------");
//			System.out.println(kMeansService.sortedCentroid(p.getCentroid()));
//			List<String> memberList = p.getRecords().stream().map(n -> n.getStudentName()).collect(Collectors.toList());
//			memberList.stream().sorted();
//			System.out.print(memberList.toString());
//			System.out.println();
//			System.out.println();
//		});

		ClimberClusterDtoList convertDTO = kMeansService.convertResultToDTO(bookingNumber, clusters);
		resp.setData(Arrays.asList(convertDTO));
		return ResponseEntity.ok(resp);
	}
}
