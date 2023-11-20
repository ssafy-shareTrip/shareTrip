package com.orj.sharetrip.trip.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.orj.sharetrip.trip.model.TripDto;

@Mapper
public interface TripMapper {

	void createTrip(TripDto tripDto);

	void pickAttr(TripDto tripDto);

	void joinGroup(TripDto tripDto);

}
