package com.orj.sharetrip.trip.model.service;

import java.util.List;

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
	public void modifyTrip(TripDto tripDto) {
		TripMapper.modifyTrip(tripDto);
		TripMapper.deletePickAttr(tripDto.getTripNo());
		TripMapper.pickAttr(tripDto);
//		TripMapper.joinGroup(tripDto);
	}

	@Override
	public void initTrip(TripDto tripDto) {
		TripMapper.initTrip(tripDto);
		TripMapper.pickAttr(tripDto);
	}

	@Override
	public void deleteTrip(Integer tripNo, String userId) {
		TripMapper.deletePickAttr(tripNo);
		TripMapper.deleteGroup(tripNo);
		TripMapper.deleteTrip(tripNo, userId);
	}

	@Override
	public void insertGroupUser(Integer tripNo, String userId) {
		TripMapper.insertGroupUser(tripNo, userId);
	}

	@Override
	public void deleteGroupUser(Integer tripNo, String userId) {
		TripMapper.deleteGroupUser(tripNo, userId);
	}

	@Override
	public TripDto detailTrip(Integer tripNo) {
		return TripMapper.detailTrip(tripNo);
	}

	@Override
	public List<TripDto> getTrip(String userId) {
		return TripMapper.getTrip(userId);
	}

}
