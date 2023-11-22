package com.orj.sharetrip.board.controller;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.orj.sharetrip.board.model.BoardDto;
import com.orj.sharetrip.board.model.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/board")
@Api("게시판 컨트롤러  API V1")
@Slf4j
public class BoardController {
	
	private BoardService BoardService;

	public BoardController(BoardService boardService) {
		super();
		BoardService = boardService;
	}

	@ApiOperation(value = "게시판 글작성", notes = "새로운 게시글 정보를 입력한다.")
	@PostMapping
	public ResponseEntity<Map<String, Object>> writeArticle(
			@RequestBody @ApiParam(value = "게시글 정보.", required = true) BoardDto boardDto) {
		log.info("writeArticle boardDto - {}", boardDto);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		try {
			BoardService.writeArticle(boardDto);
			resultMap.put("message", "글 쓰기 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("## 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = Map.class)
	@GetMapping
	public ResponseEntity<Map<String, Object>> listArticle(
			@RequestParam @ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) Map<String, Object> map) {
		log.info("listArticle map - {}", map);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		try {
			List<BoardDto> list = BoardService.listArticle(map);
			resultMap.put("data",list);
			resultMap.put("message", "글 목록 조회 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("## 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "게시판 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = Map.class)
	@GetMapping("/{boardNo}")
	public ResponseEntity<Map<String, Object>> getArticle(
			@PathVariable("boardNo") @ApiParam(value = "얻어올 글의 글번호.", required = true) int boardNo,
			@RequestParam(name = "userId", required = false) @ApiParam(value = "로그인한 유저의 아이디.") String userId)
			throws Exception {
		log.info("getArticle - 호출 : " + boardNo,userId);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		try {
			BoardService.updateHit(boardNo);
			BoardDto boardDto = BoardService.getArticle(boardNo,userId);
			resultMap.put("data", boardDto);
			resultMap.put("message", "글 상세 조회 성공");
		}catch (Exception e) {
			log.debug("## 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "수정 할 글 얻기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/modify/{boardNo}")
	public ResponseEntity<Map<String, Object>> getModifyArticle(
			@PathVariable("boardNo") @ApiParam(value = "얻어올 글의 글번호.", required = true) int boardNo)
			throws Exception {
		log.info("getModifyArticle - 호출 : " + boardNo);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		try {
			BoardDto boardDto = BoardService.getArticle(boardNo, null);
			resultMap.put("data", boardDto);
			resultMap.put("message", "글 수정 조회 성공");
		} catch (Exception e) {
			log.debug("## 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "게시판 글수정", notes = "수정할 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<Map<String, Object>> modifyArticle(
			@RequestBody @ApiParam(value = "수정할 글정보.", required = true) BoardDto boardDto) throws Exception {
		log.info("modifyArticle - 호출 {}", boardDto);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		try {
			BoardService.modifyArticle(boardDto);
			resultMap.put("message", "글 상세 수정 성공");
		} catch (Exception e) {
			log.debug("## 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다.", response = Map.class)
	@DeleteMapping("/{boardNo}")
	public ResponseEntity<Map<String, Object>> deleteArticle(@PathVariable("boardNo") @ApiParam(value = "삭제할 글의 글번호.", required = true) int boardNo) throws Exception {
		log.info("deleteArticle - 호출");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			BoardService.deleteArticle(boardNo);
			resultMap.put("message", "글 삭제 성공");
		} catch (Exception e) {
			log.debug("## 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	
	
}
