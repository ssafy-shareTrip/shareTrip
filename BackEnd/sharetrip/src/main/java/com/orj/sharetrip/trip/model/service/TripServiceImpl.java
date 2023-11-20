package com.orj.sharetrip.trip.model.service;

import org.springframework.stereotype.Service;

import com.orj.sharetrip.trip.model.TripDto;
import com.orj.sharetrip.trip.model.mapper.TripMapper;

@Service
public class TripServiceImpl implements TripService {
	
	private TripMapper TripMapper;
	
	public TripServiceImpl(com.orj.sharetrip.trip.model.mapper.TripMapper tripMapper) {
		super();
		TripMapper = tripMapper;
	}

	@Override
	public void createTrip(TripDto tripDto) {
		TripMapper.createTrip(tripDto);
		
		System.out.println(tripDto);
		
		TripMapper.pickAttr(tripDto);
		TripMapper.joinGroup(tripDto);
	}

}
