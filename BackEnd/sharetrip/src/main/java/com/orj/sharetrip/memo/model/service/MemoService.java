package com.orj.sharetrip.memo.model.service;

import java.util.Map;

public interface MemoService {

	void registAttrMemo(Map<String, Object> map);

	void deletetAttrMemo(Integer id);

	void registBoardMemo(Map<String, Object> map);

	void deletetBoardMemo(Integer id);

}
