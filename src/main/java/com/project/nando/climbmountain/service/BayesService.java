package com.project.nando.climbmountain.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;
import com.project.nando.climbmountain.bean.Attribute;
import com.project.nando.climbmountain.bean.MapAttributResult;
import com.project.nando.climbmountain.bean.Request;
import com.project.nando.climbmountain.bean.Result;
import com.project.nando.climbmountain.model.BookingDtl;
import com.project.nando.climbmountain.model.BookingHdr;
import com.project.nando.climbmountain.model.DataBayesClimber;

@Service
public class BayesService {

	@Autowired
	private DataBayesClimberService dataBayesService;

	@Autowired
	private BookingService bookingService;

	public static final Map<String, Boolean> resultMap = ImmutableMap.<String, Boolean>builder().put("butuh", true)
			.put("tidak butuh", false).build();

	@Transactional
	public BookingHdr runBayesByBookingNumber(String bookingNumber) {
		BookingHdr booking = bookingService.getBookingByBookingNumber(bookingNumber);
		if (booking == null) {
			return null;
		}

		booking.getBookingDtls().stream().forEach(p -> {
			List<Request> requests = convertBookingDtlToRequestList(p);
			Result result = run(requests);
			boolean isNeedTourGuide = resultMap.get(result.getName().toLowerCase());
			p.setNeedTourGuide(isNeedTourGuide);
			bookingService.updateBookingDtl(p);
		});

		int totalDtl = booking.getBookingDtls().size();
		int totalNeedTourGuide = (int) booking.getBookingDtls().stream().filter(p -> p.isNeedTourGuide()).count();
		int halfDtl = (int) Math.ceil(totalDtl / 2);
		
		if (totalNeedTourGuide > 0) {
			if (totalNeedTourGuide == totalDtl || totalNeedTourGuide > halfDtl) {
				booking.setNeedTourGuide(true);
				bookingService.updateBooking(booking);
			}
		}
		
		return booking;

	}

	private List<Request> convertBookingDtlToRequestList(BookingDtl dtl) {
		List<Request> requests = new ArrayList<Request>();
		Field[] fields = DataBayesClimber.class.getDeclaredFields();
		for (Field field : fields) {
			if (!field.getName().equalsIgnoreCase("id") && !field.getName().equalsIgnoreCase("result")) {
				String valueAttribute = "";
				switch (field.getName()) {
				case "age":
					valueAttribute = String.valueOf(dtl.getClimber().getAge());
					break;
				case "gender":
					valueAttribute = dtl.getClimber().getGender();
					break;
				case "hasEverClimb":
					valueAttribute = String.valueOf(dtl.getClimber().isHasEverClimb());
					break;
				case "hasDisease":
					valueAttribute = String.valueOf(dtl.getClimber().getIsHasDisease());
					break;
				default:
					break;
				}

				Request request = new Request(field.getName(), valueAttribute);
				requests.add(request);
			}
		}

//		requests.stream().forEach(p->{
//			System.out.println(p);
//		});

		return requests;

	}

	public Result run(List<Request> requests) {
		// TODO code application logic here

		// generate data from database
		List<DataBayesClimber> dataBayesList = generateData();

		// process data to count total value attribute and prob
		List<Attribute> attrs = getValueAttrs(dataBayesList);
		Map<String, Integer> mapCountResult = countResult(dataBayesList);
		Map<String, Double> mapProbResult = probResult(mapCountResult, attrs);
		List<MapAttributResult> mapAttributeResult = countAttrs(dataBayesList, attrs, mapCountResult);
		Result resultPrediction = prediction(requests, attrs, mapAttributeResult, mapProbResult, mapCountResult);
//		System.out.println("kesimpulan: " + resultPrediction.getName());

		return resultPrediction;
	}

	private List<DataBayesClimber> generateData() {
		List<DataBayesClimber> dataBayesList = dataBayesService.getDataBayesClimberAll();

//		dataBayesList.stream().forEach(p -> {
//			System.out.println(p.toString());
//		});

		return dataBayesList;

	}

	private List<Attribute> getValueAttrs(List<DataBayesClimber> dataBayesList) {
		List<Attribute> attrs = new ArrayList<Attribute>();
		Field[] fields = DataBayesClimber.class.getDeclaredFields();
		for (Field field : fields) {
			List<String> valueAttrs = new ArrayList<>();
			if (!field.getName().equalsIgnoreCase("id")) {
				dataBayesList.stream().map(p -> {
					switch (field.getName()) {
					case "age":
						return p.getAge();
					case "gender":
						return p.getGender();
					case "hasEverClimb":
						return String.valueOf(p.getHasEverClimb());
					case "hasDisease":
						return String.valueOf(p.getHasDisease());
					case "result":
						return p.getResult();
					default:
						return null;
					}
				}).distinct().forEach(q -> {
					valueAttrs.add(q);
				});
				Attribute attr = new Attribute(field.getName(), valueAttrs);
				attrs.add(attr);
			}
		}

//		attrs.forEach(p -> {
//			System.out.println("attribute: " + p.getName());
//			p.getValues().forEach(q -> {
//				System.out.println("values: " + q);
//			});
//		});
		return attrs;
	}

	private Map<String, Integer> countResult(List<DataBayesClimber> dataBayesList) {
		List<DataBayesClimber> temp = dataBayesList;
		Map<String, Integer> map = new HashMap<String, Integer>();
		temp.stream().map(p -> p.getResult()).distinct().forEach(e -> {
			int hitung = dataBayesList.stream().filter(p -> p.getResult().equals(e.toString()))
					.collect(Collectors.toList()).size();
			map.put(e, hitung);
		});

//		map.entrySet().forEach(p -> {
//			System.out.println("Jumlah Kelas " + p.getKey() + ": " + p.getValue());
//		});

		return map;
	}

	private Map<String, Double> probResult(Map<String, Integer> countResults, List<Attribute> attrs) {
		Map<String, Double> probResult = new HashMap<String, Double>();
		List<String> results = new ArrayList<String>();
		attrs.stream().filter(p -> p.getName().equalsIgnoreCase("result")).map(q -> q.getValues()).forEach(r -> {
			results.addAll(r);
		});

		for (String result : results) {
			int sumAll = countResults.values().stream().mapToInt(Integer::intValue).sum();
			int value = countResults.get(result);
			double prob = (double) value / sumAll;
			probResult.put(result, prob);
		}

//		probResult.entrySet().stream().forEach(p -> {
//			System.out.println(p.getKey() + " : " + p.getValue());
//		});

		return probResult;
	}

	public static List<MapAttributResult> countAttrs(List<DataBayesClimber> dataBayesList, List<Attribute> attrs,
			Map<String, Integer> countResults) {
		List<MapAttributResult> list = new ArrayList<MapAttributResult>();
		List<String> results = new ArrayList<String>();
		attrs.stream().filter(p -> p.getName().equalsIgnoreCase("result")).map(q -> q.getValues()).forEach(r -> {
			results.addAll(r);
		});

		List<Attribute> filterAttr = attrs.stream().filter(p -> !p.getName().equalsIgnoreCase("result"))
				.collect(Collectors.toList());

		for (Attribute attr : filterAttr) {
			for (String result : results) {
				int countResult = countResults.get(result);
				for (String attrValue : attr.getValues()) {
					long countAttr = dataBayesList.stream().filter(p -> {
						switch (attr.getName()) {
						case "age":
							return p.getResult().equalsIgnoreCase(result) && p.getAge().equalsIgnoreCase(attrValue);
						case "gender":
							return p.getResult().equalsIgnoreCase(result) && p.getGender().equalsIgnoreCase(attrValue);
						case "hasEverClimb":
							return p.getResult().equalsIgnoreCase(result)
									&& String.valueOf(p.getHasEverClimb()).equalsIgnoreCase(attrValue);
						case "hasDisease":
							return p.getResult().equalsIgnoreCase(result)
									&& String.valueOf(p.getHasDisease()).equalsIgnoreCase(attrValue);
						default:
							return false;
						}
					}).count();
					Double probAttr = (double) countAttr / (countResult);
					MapAttributResult mar = new MapAttributResult(attr.getName(), attrValue, result, countAttr,
							probAttr);
					list.add(mar);
				}
			}
		}

//		list.stream().forEach(p -> {
//			System.out.println(p.getNameAttribute() + "-" + p.getValueAttriute() + " " + p.getResult() + " count: "
//					+ p.getCount() + " prob: " + p.getProb());
//		});

		return list;
	}

	public Result prediction(List<Request> requests, List<Attribute> attrs, List<MapAttributResult> mapAttrs,
			Map<String, Double> probResults, Map<String, Integer> countResults) {
		List<String> resultAttrs = new ArrayList();

		attrs.stream().filter(p -> p.getName().equalsIgnoreCase("result")).map(m -> m.getValues()).forEach(q -> {
			resultAttrs.addAll(q);
		});

		List<Result> results = new ArrayList();
		for (String resultAttr : resultAttrs) {
			double probResult = probResults.get(resultAttr);
//			System.out.println("prob " + resultAttr + ": " + probResult);
			for (MapAttributResult mapAttr : mapAttrs) {
				if (resultAttr.equalsIgnoreCase(mapAttr.getResult())) {
					for (Request request : requests) {
						if (request.getNameAttribute().equalsIgnoreCase(mapAttr.getNameAttribute())) {
							if (request.getValueAttribute().equalsIgnoreCase(mapAttr.getValueAttriute())) {
								boolean checkZero = requests.stream().filter(p -> {
									return mapAttrs.stream().filter(q -> {
										return p.getNameAttribute().equalsIgnoreCase(q.getNameAttribute())
												&& p.getValueAttribute().equalsIgnoreCase(q.getValueAttriute())
												&& q.getCount() == 0 && q.getResult().equalsIgnoreCase(resultAttr);
									}).findAny().isPresent();
								}).findAny().isPresent();
//								System.out.println("mapAttr: " + mapAttr.toString());
//								System.out.println("checkZero: " + checkZero);
								if (checkZero) {
									long countAttr = mapAttr.getCount();
									++countAttr;
									int sumCountResult = countResults.values().stream().mapToInt(Integer::intValue)
											.sum();
									double newProb = (double) countAttr / sumCountResult;
									probResult *= newProb;
//									System.out.println("countAttr: " + countAttr + " - sumCountResult: "
//											+ sumCountResult + " - newProb: " + newProb + " - prob: " + probResult);
								} else {
									probResult *= mapAttr.getProb();
//									System.out.println("prob: " + probResult);
								}
							}
						}
					}
				}
			}
			Result result = new Result(resultAttr, probResult);
			results.add(result);
		}

//		mapAttrs.stream().forEach(p -> {
//			System.out.println(p.toString());
//		});

//		results.stream().forEach(p -> {
//			System.out.println(p.getName() + ": " + p.getValue());
//		});

		Result max = results.stream().max(Comparator.comparing(Result::getValue)).orElse(null);

		return max;
	}
}
