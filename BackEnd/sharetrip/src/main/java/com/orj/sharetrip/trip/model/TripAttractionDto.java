package com.orj.sharetrip.trip.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "TripAttractionDto : 경로 관광지 정보", description = "경로에 등록된 관광지를 나타낸다.")
public class TripAttractionDto {
	
	private int contentId;
	private String title;
	private String image;
	private int day;
	private int seq;
	private String pickTime;
	
}
//id int AI PK 
//trip_no int 
//content_id int 
//day int 
//order int 
//pick_time datetime