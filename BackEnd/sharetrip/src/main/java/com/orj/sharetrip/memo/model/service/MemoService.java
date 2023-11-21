package com.orj.sharetrip.memo.model.service;

import java.util.Map;

import com.orj.sharetrip.memo.model.MemoDto;

public interface MemoService {

	void registAttrMemo(MemoDto memoDto);

	void deletetAttrMemo(Integer id);

	void registBoardMemo(MemoDto memoDto);

	void deletetBoardMemo(Integer id);

}
