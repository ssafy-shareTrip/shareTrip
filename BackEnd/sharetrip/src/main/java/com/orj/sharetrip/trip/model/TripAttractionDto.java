package com.orj.sharetrip.trip.model;

import lombok.Data;

@Data
public class TripAttractionDto {
	
	private int contentId;
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