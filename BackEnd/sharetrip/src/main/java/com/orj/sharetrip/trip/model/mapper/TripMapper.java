package com.orj.sharetrip.trip.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.orj.sharetrip.trip.model.TripDto;

@Mapper
public interface TripMapper {

	void modifyTrip(TripDto tripDto);

	void pickAttr(TripDto tripDto);

//	void joinGroup(TripDto tripDto);

	void initTrip(TripDto tripDto);

	void deletePickAttr(Integer tripNo);

	void deleteTrip(Integer tripNo, String userId);

	void deleteGroup(Integer tripNo);

	void insertGroupUser(Integer tripNo, String userId);

	void deleteGroupUser(Integer tripNo, String userId);

	TripDto detailTrip(Integer tripNo);

	
}
