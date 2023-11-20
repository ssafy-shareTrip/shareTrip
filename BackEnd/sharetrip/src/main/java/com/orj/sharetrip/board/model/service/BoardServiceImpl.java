package com.orj.sharetrip.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.orj.sharetrip.board.model.BoardDto;
import com.orj.sharetrip.board.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	private BoardMapper BoardMapper;
	
	public BoardServiceImpl(BoardMapper boardMapper) {
		super();
		BoardMapper = boardMapper;
	}

	@Override
	public void writeArticle(BoardDto boardDto) throws Exception {
		BoardMapper.writeArticle(boardDto);
	}

	@Override
	public List<BoardDto> listArticle(Map<String, Object> map) throws Exception {
		return BoardMapper.listArticle(map);
	}

	@Override
	public BoardDto getArticle(int boardNo) throws Exception {
		return BoardMapper.getArticle(boardNo);
	}

	@Override
	public void updateHit(int boardNo) throws Exception {
		BoardMapper.updateHit(boardNo);
	}

	@Override
	public void modifyArticle(BoardDto boardDto) throws Exception {
		BoardMapper.modifyArticle(boardDto);
	}

	@Override
	public void deleteArticle(int boardNo) throws Exception {
		BoardMapper.deleteArticle(boardNo);
	}

}
