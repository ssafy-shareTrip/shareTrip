package com.orj.sharetrip.memo.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "MemoDto : 댓글정보", description = "댓글의 상세 정보를 나타낸다.")
public class MemoDto {
	private Object target;
	private int id;
	private String userId;
	private String userName;
	private String content;
	private String registTime;
	
}
