package com.orj.sharetrip.trip.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orj.sharetrip.trip.model.TripDto;
import com.orj.sharetrip.trip.model.service.TripService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

	@ApiOperation(value = "경로 정보 조회", notes = "저장되어있는 경로 정보를 조회합니다", response = Map.class)
	@GetMapping("/{userId}")
	public ResponseEntity<Map<String, Object>> getTrip(
			@PathVariable("userId") @ApiParam(value = "유저 ID") String userId) throws Exception {
		log.info("경로 정보 조회");
		log.debug(" info : {}",userId);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		try {
			List<TripDto> list = TripService.getTrip(userId);
			resultMap.put("data", list);
			resultMap.put("message", "상세 조회 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("## 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	@ApiOperation(value = "경로 정보 조회", notes = "저장되어있는 경로 정보를 조회합니다", response = Map.class)
	@GetMapping("/detail/{tripNo}")
	public ResponseEntity<Map<String, Object>> detailTrip(
			@PathVariable("tripNo") @ApiParam(value = "경로 ID") Integer tripNo) throws Exception {
		log.info("경로 정보 조회");
		log.debug(" info : {}");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		try {
			TripDto tripDto = TripService.detailTrip(tripNo);
			resultMap.put("data", tripDto);
			resultMap.put("message", "상세 조회 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("## 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "경로 초기 생성", notes = "새로 만든 경로를 생성합니다. user_id 하나만 사용 됨. tripNo를 리턴", response = Map.class)
	@PostMapping("/init")
	public ResponseEntity<Map<String, Object>> initTrip( 
			@RequestBody TripDto tripDto) throws Exception {
		log.info("경로 초기 생성");
		log.debug(" info : {}", tripDto);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		try {
			TripService.initTrip(tripDto);
			resultMap.put("data", tripDto.getTripNo());
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
	
	@ApiOperation(value = "경로 저장", notes = "경로를 저장합니다. title, isShared, TripAttraction 변경", response = Map.class)
	@PutMapping("/modify/{tripNo}")
	public ResponseEntity<Map<String, Object>> modifyTrip( 
			@PathVariable("tripNo") @ApiParam(value = "경로 ID") Integer tripNo,
			@RequestBody TripDto tripDto) throws Exception {
		log.info("경로 초기 생성");
		log.debug(" info : {}", tripDto);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		tripDto.setTripNo(tripNo);
		try {
			TripService.modifyTrip(tripDto);
			resultMap.put("data", tripDto.getTripNo());
			resultMap.put("message", "경로 수정 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("## 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "경로 삭제", notes = "경로를 삭제합니다.", response = Map.class)
	@DeleteMapping("/delete/{tripNo}")
	public ResponseEntity<Map<String, Object>> deleteTrip( 
			@PathVariable("tripNo") @ApiParam(value = "경로 ID") Integer tripNo,
			@RequestParam("userId") @ApiParam(value = "로그인한 유저 ID") String userId) throws Exception {
		log.info("경로 삭제");
		log.debug(" info : {}, {}", tripNo, userId);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			TripService.deleteTrip( tripNo, userId);
			resultMap.put("message", "경로 삭제 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("## 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	@ApiOperation(value = "그룹 사용자 추가", notes = "경로 그룹에 사용자를 추가합니다", response = Map.class)
	@PostMapping("/group/{userId}")
	public ResponseEntity<Map<String, Object>> insertGroupUser(
			@PathVariable("userId") @ApiParam(value = "그룹에 추가할 유저 ID") String userId,
			@RequestParam("tripNo") @ApiParam(value = "경로 ID") Integer tripNo) throws Exception {
		log.info("그룹 사용자 추가");
		log.debug(" info : {}, {}", userId, tripNo);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		try {
			TripService.insertGroupUser(tripNo, userId);
			resultMap.put("message", "사용자 추가 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("## 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "그룹 사용자 삭제", notes = "경로 그룹에 사용자를 삭제합니다", response = Map.class)
	@DeleteMapping("/group/{userId}")
	public ResponseEntity<Map<String, Object>> deleteGroupUser(
			@PathVariable("userId") @ApiParam(value = "그룹에서 삭제할 유저 ID") String userId,
			@RequestParam("tripNo") @ApiParam(value = "경로 ID") Integer tripNo) throws Exception {
		log.info("그룹 사용자 삭제");
		log.debug(" info : {}, {}", userId, tripNo);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		try {
			TripService.deleteGroupUser(tripNo, userId);
			resultMap.put("message", "사용자 추가 성공");
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
