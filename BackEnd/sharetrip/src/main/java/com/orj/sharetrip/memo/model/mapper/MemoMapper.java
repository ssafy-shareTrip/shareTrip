package com.orj.sharetrip.memo.model.mapper;

import java.util.Map;

import com.orj.sharetrip.memo.model.MemoDto;

public interface MemoMapper {

	void registAttrMemo(MemoDto memoDto);

	void deletetAttrMemo(Integer id);

	void registBoardMemo(MemoDto memoDto);

	void deleteBoardMemo(Integer id);


}
