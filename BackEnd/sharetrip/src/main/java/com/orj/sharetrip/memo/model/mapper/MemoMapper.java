package com.orj.sharetrip.memo.model.mapper;

import java.util.Map;

public interface MemoMapper {

	void registAttrMemo(Map<String, Object> map);

	void deletetAttrMemo(Integer id);

	void registBoardMemo(Map<String, Object> map);

	void deleteBoardMemo(Integer id);


}
