package com.orj.sharetrip.map.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orj.sharetrip.map.model.AttractionDto;
import com.orj.sharetrip.map.model.SidoGugunCodeDto;
import com.orj.sharetrip.map.model.service.MapService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/map")
@Api("Map 컨트롤러  API V1")
@Slf4j
public class MapController {
	
	private MapService MapService;

	public MapController(MapService mapService) {
		super();
		this.MapService = mapService;
	}

	@ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.", response = Map.class)
	@GetMapping("/sido")
	public ResponseEntity<Map<String, Object>> sido() throws Exception {
		log.info("sido - 호출");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		List<SidoGugunCodeDto> list = null;
		
		try {
			list = MapService.getSido();
			resultMap.put("data", list);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("시도 코드 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "구군 정보", notes = "시도에 속한 구군을 반환한다.", response = Map.class)
	@GetMapping("/gugun")
	public ResponseEntity<Map<String, Object>> gugun(
			@RequestParam("sido") @ApiParam(value = "시도코드.", required = true) String sido) throws Exception {
		log.info("gugun - 호출");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		List<SidoGugunCodeDto> list = null;
		
		try {
			list = MapService.getGugunInSido(sido);
			resultMap.put("data", list);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("구군 코드 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "관광지 리스트 정보", notes = "관광지 리스트를 반환한다.", response = Map.class)
	@GetMapping("/attr")
	public ResponseEntity<Map<String, Object>> getAttrList(
			@RequestParam(name = "sido", required = false) @ApiParam(value = "시도코드.") String sidoCode,
			@RequestParam(name = "gugun", required = false) @ApiParam(value = "구군코드.") String gugunCode,
			@RequestParam(name = "contentTypeId", required = false) @ApiParam(value = "관광타입(12:관광지, 14:문화시설, 15:축제공연행사, 25:여행코스, 28:레포츠, 32:숙박, 38:쇼핑, 39:음식점) ID.") String contentTypeId,
			@RequestParam(name = "keyword", required = false) @ApiParam(value = "검색 키워드.") String keyword,
			@RequestParam(name = "mapX", required = false) @ApiParam(value = "GPS X좌표(WGS84 경도좌표).") Double mapX,
			@RequestParam(name = "mapY", required = false) @ApiParam(value = "GPS Y좌표(WGS84 위도좌표).") Double mapY) throws Exception {
		log.info("관광지 조회");
		log.debug(" info : {}, {}, {}, {}, {}, {}",sidoCode,gugunCode,contentTypeId, keyword, mapX, mapY);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		List<AttractionDto> list = null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidoCode", sidoCode);
		map.put("gugunCode", gugunCode);
		map.put("contentTypeId", contentTypeId);
		map.put("keyword", keyword);
		map.put("mapX", mapX);
		map.put("mapY", mapY);
		
		try {
			list = MapService.getAttrList(map);
			resultMap.put("data", list);
			resultMap.put("message", "조회 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("관광지 조회 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "관광지 정보", notes = "관광지에 대한 상세 정보를 반환한다.", response = Map.class)
	@GetMapping("/attr/{contentId}")
	public ResponseEntity<Map<String, Object>> getAttrInfo(
			@PathVariable("contentId") @ApiParam(value = "관광지 ID.", required = true) String contentId) throws Exception {
		log.info("관광지 상세 조회");
		log.debug(" info : {}", contentId);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		List<AttractionDto> list = null;
		try {
			AttractionDto attr = MapService.getAttrInfo(contentId);
			resultMap.put("data", attr);
			resultMap.put("message", "상세 조회 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("상세 조화 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	

	

	
	
	
}
