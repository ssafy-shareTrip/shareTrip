package com.orj.sharetrip.trip.model.service;

import java.util.List;

import com.orj.sharetrip.trip.model.TripDto;

public interface TripService {

	void modifyTrip(TripDto tripDto);

	void initTrip(TripDto tripDto);

	void deleteTrip(Integer tripNo, String userId);

	void insertGroupUser(Integer tripNo, String userId);
	
	void deleteGroupUser(Integer tripNo, String userId);

	TripDto detailTrip(Integer tripNo);

	List<TripDto> getTrip(String userId);

	List<TripDto> getShareTrip();

	List<TripDto> getGroupTrip(String userId);

}
