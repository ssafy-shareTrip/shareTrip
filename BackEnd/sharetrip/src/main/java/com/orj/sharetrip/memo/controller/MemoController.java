package com.orj.sharetrip.memo.controller;

import java.util.HashMap;
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

import com.orj.sharetrip.memo.model.MemoDto;
import com.orj.sharetrip.memo.model.service.MemoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/memo")
@Api("Memo 컨트롤러  API V1")
@Slf4j
public class MemoController {
	
	private MemoService MemoService;
	
	public MemoController(com.orj.sharetrip.memo.model.service.MemoService memoService) {
		super();
		MemoService = memoService;
	}

	@ApiOperation(value = "관광지 댓글 쓰기", notes = "관광지에 대한 댓글 쓰기 기능. contentId, { \"userId\" : \"\", \"content\" : \"\" }", response = Map.class)
	@PostMapping("/attr/{contentId}")
	public ResponseEntity<Map<String, Object>> registAttrMemo(
			@PathVariable("contentId") @ApiParam(value = "관광지 ID.", required = true) String contentId,
			@RequestBody MemoDto memoDto ) throws Exception {
		log.info("관광지 댓글 쓰기");
		log.debug(" info : {}, {}", contentId, memoDto);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		memoDto.setTarget(contentId);
		try {
			MemoService.registAttrMemo(memoDto);
			resultMap.put("data", memoDto);
			resultMap.put("message", "관광지 댓글 등록 성공");
			status = HttpStatus.CREATED;
		} catch (Exception e) {
			log.debug("댓글 쓰기 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "관광지 댓글 삭제", notes = "관광지에 대한 댓글 삭제 기능", response = Map.class)
	@DeleteMapping("/attr/{id}")
	public ResponseEntity<Map<String, Object>> deleteAttrMemo(
			@PathVariable("id") @ApiParam(value = "댓글 번호.", required = true) Integer id) throws Exception {
		log.info("관광지 댓글 삭제");
		log.debug(" info : {}", id);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			MemoService.deletetAttrMemo(id);
			resultMap.put("message", "관광지 댓글 삭제 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("댓글 삭제 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	

	@ApiOperation(value = "게시글 댓글 쓰기", notes = "게시글에 대한 댓글 쓰기 기능", response = Map.class)
	@PostMapping("/board/{boardNo}")
	public ResponseEntity<Map<String, Object>> registBoardMemo(
			@PathVariable("boardNo") @ApiParam(value = "게시글 번호.", required = true) Integer boardNo,
			@RequestBody MemoDto memoDto) throws Exception {
		log.info("게시글 댓글 쓰기");
		log.debug(" info : {}, {}", boardNo, memoDto);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		memoDto.setTarget(boardNo);
		try {
			MemoService.registBoardMemo(memoDto);
			resultMap.put("data", memoDto);
			resultMap.put("message", "게시글 댓글 등록 성공");
			status = HttpStatus.CREATED;
		} catch (Exception e) {
			log.debug("게시글 댓글 쓰기 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "게시글 댓글 삭제", notes = "게시글에 대한 댓글 삭제 기능", response = Map.class)
	@DeleteMapping("/board/{id}")
	public ResponseEntity<Map<String, Object>> deleteBoardMemo(
			@PathVariable("id") @ApiParam(value = "댓글 번호.", required = true) Integer id) throws Exception {
		log.info("게시글 댓글 삭제");
		log.debug(" info : {}, {}", id);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			MemoService.deletetBoardMemo(id);
			resultMap.put("message", "관광지 댓글 삭제 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("댓글 삭제 에러 발생 : {}", e);
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
