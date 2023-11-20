package com.orj.sharetrip.trip.model;

import java.util.List;

import lombok.Data;

@Data
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