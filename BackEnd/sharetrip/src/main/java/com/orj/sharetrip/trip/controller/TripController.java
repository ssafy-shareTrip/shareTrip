package com.orj.sharetrip.trip.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orj.sharetrip.trip.model.TripDto;
import com.orj.sharetrip.trip.model.service.TripService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/trip")
@Api("Trip 컨트롤러  API V1")
@Slf4j
public class TripController {

	private TripService TripService;
	
	public TripController(com.orj.sharetrip.trip.model.service.TripService tripService) {
		super();
		TripService = tripService;
	}

	@ApiOperation(value = "경로 초기 생성", notes = "새로 만든 경로를 저장합니다", response = Map.class)
	@PostMapping("/create")
	public ResponseEntity<Map<String, Object>> createTrip( 
			@RequestBody TripDto tripDto) throws Exception {
		log.info("경로 초기 생성");
		log.debug(" info : {}", tripDto);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		try {
			TripService.createTrip(tripDto);
			resultMap.put("message", "경로 생성 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("## 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	
//	@ApiOperation(value = "요약 정보", notes = "상세 정보", response = Map.class)
//	@GetMapping("url")
//	public ResponseEntity<Map<String, Object>> temp( ) throws Exception {
//		log.info("메소드 설명");
//		log.debug(" info : {}");
//		
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		HttpStatus status = HttpStatus.ACCEPTED;
//		
//		try {
//			resultMap.put("data", null);
//			resultMap.put("message", "상세 조회 성공");
//			status = HttpStatus.OK;
//		} catch (Exception e) {
//			log.debug("## 에러 발생 : {}", e);
//			resultMap.put("message", e.getMessage());
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//		
//		resultMap.put("status", status);
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	}
	
}
