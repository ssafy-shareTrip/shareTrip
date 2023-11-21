package com.orj.sharetrip.memo.model.mapper;

import java.util.Map;

public interface MemoMapper {

	void registAttrMemo(Map<String, Object> map);

	void deletetAttrMemo(Map<String, Object> map);

	void registBoardMemo(Map<String, Object> map);

	void deleteBoardMemo(Map<String, Object> map);


}
