package com.orj.sharetrip.board.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.orj.sharetrip.board.model.BoardDto;

@Mapper
public interface BoardMapper {
	void writeArticle(BoardDto boardDto) throws SQLException;

	List<BoardDto> listArticle(Map<String, Object> param) throws SQLException;

	int getTotalArticleCount(Map<String, Object> param) throws SQLException;

	BoardDto getArticle(int boardNo) throws SQLException;

	void updateHit(int boardNo) throws SQLException;

	void modifyArticle(BoardDto boardDto) throws SQLException;

	void deleteArticle(int boardNo) throws SQLException;

}
