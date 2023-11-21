package com.orj.sharetrip.memo.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.orj.sharetrip.memo.model.MemoDto;
import com.orj.sharetrip.memo.model.mapper.MemoMapper;

@Service
public class MemoServiceImpl implements MemoService{

	MemoMapper MemoMapper;
	
	public MemoServiceImpl(com.orj.sharetrip.memo.model.mapper.MemoMapper memoMapper) {
		super();
		MemoMapper = memoMapper;
	}


	@Override
	public void registAttrMemo(MemoDto memoDto) {
		MemoMapper.registAttrMemo(memoDto);
	}


	@Override
	public void deletetAttrMemo(Integer id) {
		MemoMapper.deletetAttrMemo(id);
	}


	@Override
	public void registBoardMemo(MemoDto memoDto) {
		MemoMapper.registBoardMemo(memoDto);
	}


	@Override
	public void deletetBoardMemo(Integer id) {
		MemoMapper.deleteBoardMemo(id);
	}

}
