package com.orj.sharetrip.fav.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.orj.sharetrip.fav.model.FavoriteDto;
import com.orj.sharetrip.fav.model.mapper.FavMapper;

@Service
public class FavServiceImpl implements FavService {

	private FavMapper FavMapper;
	
	public FavServiceImpl(com.orj.sharetrip.fav.model.mapper.FavMapper favMapper) {
		super();
		FavMapper = favMapper;
	}


	@Override
	public List<FavoriteDto> getAttrFav(String userId) {
		return FavMapper.getAttrFav(userId);
	}


	@Override
	public void deleteAttrFav(Map<String, Object> map) {
		FavMapper.deleteAttrFav(map);
	}


	@Override
	public void registAttrFav(Map<String, Object> map) {
		FavMapper.registAttrFav(map);
	}

}
