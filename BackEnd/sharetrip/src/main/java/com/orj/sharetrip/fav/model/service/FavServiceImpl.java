package com.orj.sharetrip.fav.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.orj.sharetrip.fav.model.FavoriteDto;
import com.orj.sharetrip.fav.model.mapper.FavMapper;
import com.orj.sharetrip.map.model.AttractionDto;

@Service
public class FavServiceImpl implements FavService {

	private FavMapper FavMapper;

	public FavServiceImpl(com.orj.sharetrip.fav.model.mapper.FavMapper favMapper) {
		super();
		FavMapper = favMapper;
	}

	@Override
	public List<AttractionDto> getAttrFav(String userId, Integer category) {
		return FavMapper.getAttrFav(userId, category);
	}

	@Override
	public void deleteAttrFav(String userId, Integer contentId, Integer category) {
		FavMapper.deleteAttrFav(userId, contentId, category);
	}

	@Override
	public void registAttrFav(Map<String, Object> map) {
		FavMapper.registAttrFav(map);
	}

	@Override
	public int getAttrFavCount(Integer contentId, Integer category) {
		return FavMapper.getAttrFavCount(contentId, category);
	}

}
