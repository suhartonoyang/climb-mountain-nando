package com.project.nando.climbmountain.service.kmeans;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.nando.climbmountain.bean.Centroid;
import com.project.nando.climbmountain.bean.ClimberClusterDto;
import com.project.nando.climbmountain.bean.ClimberClusterDtoList;
import com.project.nando.climbmountain.bean.Cluster;
import com.project.nando.climbmountain.bean.Record;
import com.project.nando.climbmountain.model.BookingDtl;
import com.project.nando.climbmountain.model.BookingHdr;
import com.project.nando.climbmountain.service.BookingService;
import com.project.nando.climbmountain.utils.Distance;

@Service
public class KMeansService {

	@Autowired
	private BookingService bookingService;

	/**
	 * Performs the K-Means clustering algorithm on the given dataset.
	 *
	 * @param records       The dataset.
	 * @param k             Number of Clusters.
	 * @param distance      To calculate the distance between two items.
	 * @param maxIterations Upper bound for the number of iterations.
	 * @return K clusters along with their features.
	 */
	public List<Cluster> run(String bookingNumber, int totalClusters, Distance distance, int maxIterations) {
		List<Record> data = generateData(bookingNumber);
		Map<Centroid, List<Record>> clusters = KMeans.fit(data, totalClusters, new EuclideanDistance(), maxIterations);
		List<Cluster> clustersList = new ArrayList<>();
		AtomicInteger clusterNumber = new AtomicInteger(1);
		clusters.entrySet().forEach(p -> {
			Cluster cluster = new Cluster();
			cluster.setCentroid(p.getKey());
			cluster.setRecords(p.getValue());
			cluster.setClusterNumber(clusterNumber.getAndIncrement());
			clustersList.add(cluster);
		});
		return clustersList;
	}

	private List<Record> generateData(String bookingNumber) {
		BookingHdr booking = bookingService.getBookingByBookingNumber(bookingNumber);
		List<Record> data = new ArrayList<>();

		for (BookingDtl dtl : booking.getBookingDtls()) {
			int climberId = dtl.getClimber().getId();
			String climberName = dtl.getClimber().getName();
			boolean isLeader = dtl.getClimber().isLeader();
			int age = dtl.getClimber().getAge();
			int countDiseases = dtl.getClimber().getClimberDiseases().size();
			int numberOfClimbs = dtl.getClimber().getNumberOfClimbs();

			Map<String, Double> features = new HashMap<String, Double>();
			features.put("age", (double) age);
			features.put("countDiseases", (double) countDiseases);
			features.put("numberOfClimbs", (double) numberOfClimbs);

			if (!features.isEmpty()) {
				Record record = new Record(climberId, climberName, isLeader, features);
				data.add(record);
			}

		}

//		data.forEach(p -> {
//			System.out.println("climber: " + p.getClimberId() + " - " + p.getClimberName());
//			p.getFeatures().entrySet().forEach(a -> {
//				System.out.println("features: " + a.getKey() + " - " + a.getValue());
//			});
//		});

		return data;
	}

	public Centroid sortedCentroid(Centroid key) {
		List<Map.Entry<String, Double>> entries = new ArrayList<>(key.getCoordinates().entrySet());
		entries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

		Map<String, Double> sorted = new LinkedHashMap<>();
		for (Map.Entry<String, Double> entry : entries) {
			sorted.put(entry.getKey(), entry.getValue());
		}

		return new Centroid(sorted);
	}

	public ClimberClusterDtoList convertResultToDTO(String bookingNumber, List<Cluster> clusters) {
		int totalCluster = clusters.size();
		List<ClimberClusterDto> convertList = new ArrayList<ClimberClusterDto>();
		clusters.stream().forEach(p -> {
			p.getRecords().stream().forEach(q -> {
				ClimberClusterDto convert = new ClimberClusterDto(q.getClimberId(), q.getClimberName(), q.isLeader(),
						p.getClusterNumber());
				convertList.add(convert);
			});
		});

		convertList.stream().sorted(Comparator.comparingInt(ClimberClusterDto::getClusterNumber)
				.thenComparing(Comparator.comparingInt(ClimberClusterDto::getClimberId)));

		ClimberClusterDtoList convertResult = new ClimberClusterDtoList(clusters.size(), bookingNumber, convertList);

		return convertResult;
	}

}
