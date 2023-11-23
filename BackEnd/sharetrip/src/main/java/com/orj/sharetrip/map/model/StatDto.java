package com.orj.sharetrip.map.model;

import java.util.List;

import lombok.Data;

@Data
public class StatDto {

	private List<AttractionDto> readCntOrder;
	private List<AttractionDto> likeOrder;
	private List<AttractionDto> bookmarkOrder;
	private List<AttractionDto> pathOrder;
	private List<AttractionDto> genderOrder;
	private List<AttractionDto> birthOrder;
	
}
