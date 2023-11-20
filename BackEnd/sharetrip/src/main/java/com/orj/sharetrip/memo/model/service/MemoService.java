package com.orj.sharetrip.memo.model.service;

import java.util.Map;

public interface MemoService {

	void registAttrMemo(Map<String, Object> map);

	void deletetAttrMemo(Map<String, Object> map);

	void registBoardMemo(Map<String, Object> map);

	void deletetBoardMemo(Map<String, Object> map);

}
