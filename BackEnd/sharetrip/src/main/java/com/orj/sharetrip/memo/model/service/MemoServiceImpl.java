package com.orj.sharetrip.memo.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.orj.sharetrip.memo.model.mapper.MemoMapper;

@Service
public class MemoServiceImpl implements MemoService{

	MemoMapper MemoMapper;
	
	public MemoServiceImpl(com.orj.sharetrip.memo.model.mapper.MemoMapper memoMapper) {
		super();
		MemoMapper = memoMapper;
	}


	@Override
	public void registAttrMemo(Map<String, Object> map) {
		MemoMapper.registAttrMemo(map);
	}


	@Override
	public void deletetAttrMemo(Map<String, Object> map) {
		MemoMapper.deletetAttrMemo(map);
	}

}
