package com.orj.sharetrip.fav.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orj.sharetrip.board.model.BoardDto;
import com.orj.sharetrip.fav.model.FavoriteDto;
import com.orj.sharetrip.fav.model.service.FavService;
import com.orj.sharetrip.map.model.AttractionDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/fav")
@Api("Fav 컨트롤러  API V1")
@Slf4j
public class FavController {

	private FavService FavService;
	
	public FavController(com.orj.sharetrip.fav.model.service.FavService favService) {
		super();
		FavService = favService;
	}

	@ApiOperation(value = "관광지 좋아요 개수 조회", notes = "관광지가 좋아요 또는 북마크된 개수를 가져온다.", response = Map.class)
	@GetMapping("/attrcnt/{contentId}")
	public ResponseEntity<Map<String, Object>> getAttrFavCount(
			@PathVariable("contentId") @ApiParam(value = "관광지 ID.", required = true) Integer contentId,
			@RequestParam(name = "category", required = false) Integer category) throws Exception {
		log.info("관광지 좋아요 개수 조회");
		log.debug(" info : {}", contentId);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		try {
			int count = FavService.getAttrFavCount(contentId, category);
			resultMap.put("data", count);
			resultMap.put("message", "관광지 Fav 정보 조회 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("## 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	
	@ApiOperation(value = "유저의 관광지 좋아요 상태 조회", notes = "유저가 좋아요 또는 북마크한 관광지 정보를 가져온다.", response = Map.class)
	@GetMapping("/attr/{userId}")
	public ResponseEntity<Map<String, Object>> getAttrFav(
			@PathVariable("userId") @ApiParam(value = "유저 ID.", required = true) String userId,
			@RequestParam(name = "category", required = true) Integer category) throws Exception {
		log.info("관광지 좋아요 상태  조회");
		log.debug(" info : {}", userId);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		List<AttractionDto> list = null;
		
		try {
			list = FavService.getAttrFav(userId, category);
			resultMap.put("data", list);
			resultMap.put("message", "관광지 좋아요 조회 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("## 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "관광지 좋아요 상태 등록", notes = "유저가 좋아요 또는 북마크한 관광지 정보를 가져온다.", response = Map.class)
	@PostMapping("/attr/{userId}")
	public ResponseEntity<Map<String, Object>> registAttrFav(
			@PathVariable("userId") @ApiParam(value = "유저 ID.", required = true) String userId,
			@RequestBody Map<String, Object> map) throws Exception {
		log.info("관광지 좋아요 상태 등록");
		log.debug(" info : {}", map);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		map.put("userId", userId);
		try {
			FavService.registAttrFav(map);
			resultMap.put("message", "관광지 좋아요 정보 등록 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("## 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "관광지 좋아요 상태 삭제", notes = "유저가 좋아요 또는 북마크한 관광지 정보를 삭제한다.", response = Map.class)
	@DeleteMapping("/attr/{userId}")
	public ResponseEntity<Map<String, Object>> deleteAttrFav(
			@PathVariable("userId") @ApiParam(value = "유저 ID.", required = true) String userId,
			@RequestParam("contentId")  @ApiParam(value = "관광지 ID.", required = true) Integer contentId,
			@RequestParam("category")  @ApiParam(value = "카테고리.", required = true) Integer category) throws Exception {
		log.info("관광지 좋아요 상태 삭제");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			FavService.deleteAttrFav(userId, contentId,category);
			resultMap.put("message", "관광지 좋아요 정보 삭제 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("## 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "게시글 좋아요 개수 조회", notes = "게시글가 좋아요 또는 북마크된 개수를 가져온다.", response = Map.class)
	@GetMapping("/boardcnt/{boardNo}")
	public ResponseEntity<Map<String, Object>> getBoardFavCount(
			@PathVariable("boardNo") @ApiParam(value = "게시글 번호.", required = true) Integer boardNo,
			@RequestParam(name = "category", required = false) Integer category) throws Exception {
		log.info("게시글 좋아요 개수 조회");
		log.debug(" info : {}, {}", boardNo, category);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		try {
			int count = FavService.getBoardFavCount(boardNo, category);
			resultMap.put("data", count);
			resultMap.put("message", "게시글 Fav 정보 조회 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("## 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "유저의 게시글 좋아요 상태 조회", notes = "유저가 좋아요 또는 북마크한 게시글 정보를 가져온다.", response = Map.class)
	@GetMapping("/board/{userId}")
	public ResponseEntity<Map<String, Object>> getBoardFav(
			@PathVariable("userId") @ApiParam(value = "유저 ID.", required = true) String userId,
			@RequestParam(name = "category", required = true) Integer category) throws Exception {
		log.info("관광지 좋아요 상태  조회");
		log.debug(" info : {}", userId);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		List<BoardDto> list = null;
		
		try {
			list = FavService.getBoardFav(userId, category);
			resultMap.put("data", list);
			resultMap.put("message", "게시글 좋아요 조회 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("## 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "게시글 좋아요 상태 등록", notes = "유저가 좋아요 또는 북마크한 게시글 정보를 가져온다.", response = Map.class)
	@PostMapping("/board/{userId}")
	public ResponseEntity<Map<String, Object>> registBoardFav(
			@PathVariable("userId") @ApiParam(value = "유저 ID.", required = true) String userId,
			@RequestBody Map<String, Object> map) throws Exception {
		log.info("게시글 좋아요 상태 등록");
		log.debug(" info : {}", map);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		map.put("userId", userId);
		try {
			FavService.registBoardFav(map);
			resultMap.put("message", "게시글 좋아요 정보 등록 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("## 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "게시글 좋아요 상태 삭제", notes = "유저가 좋아요 또는 북마크한 게시글 정보를 삭제한다.", response = Map.class)
	@DeleteMapping("/board/{userId}")
	public ResponseEntity<Map<String, Object>> deleteBoardFav(
			@PathVariable("userId") @ApiParam(value = "유저 ID.", required = true) String userId,
			@RequestParam("boardNo")  @ApiParam(value = "글 번호.", required = true) Integer boardNo,
			@RequestParam("category")  @ApiParam(value = "카테고리.", required = true) Integer category) throws Exception {
		log.info("게시글 좋아요 상태 삭제");
		log.debug(" info : {}, {}", userId, boardNo,category);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			FavService.deleteBoardFav(userId, boardNo,category);
			resultMap.put("message", "게시글 좋아요 정보 삭제 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("## 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
}
