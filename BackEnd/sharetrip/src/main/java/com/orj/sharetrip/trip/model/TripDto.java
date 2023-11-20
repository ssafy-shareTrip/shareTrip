package com.orj.sharetrip.trip.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "TripDto : 경로정보", description = "경로의 상세 정보를 나타낸다.")
public class TripDto {

	private int tripNo;
	private String userId;
	private String title;
	private String create_time;
	private int isShared;
	private List<TripAttractionDto> attractions;
	private List<String> userIds;
	
}

//trip_no int AI PK 
//user_id varchar(45) 
//title varchar(200) 
//create_time datetime 
//is_shared tinyint



//trip_no int PK 
//user_id varchar(45) PK 
//join_date varchar(45)