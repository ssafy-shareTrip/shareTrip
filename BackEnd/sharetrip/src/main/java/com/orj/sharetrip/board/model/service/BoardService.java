package com.orj.sharetrip.board.model.service;

import java.util.List;
import java.util.Map;

import com.orj.sharetrip.board.model.BoardDto;

public interface BoardService {

	void writeArticle(BoardDto boardDto) throws Exception;
	List<BoardDto> listArticle(Map<String, Object> map) throws Exception;
//	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	BoardDto getArticle(int boardNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	
	void modifyArticle(BoardDto boardDto) throws Exception;
//	
	void deleteArticle(int boardNo) throws Exception;
	
}
