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
	public List<FavoriteDto> getAttrFav(String userId,Integer category) {
		return FavMapper.getAttrFav(userId,category);
	}


	@Override
	public void deleteAttrFav(String userId,Integer contentId) {
		FavMapper.deleteAttrFav(userId,contentId);
	}


	@Override
	public void registAttrFav(Map<String, Object> map) {
		FavMapper.registAttrFav(map);
	}


	@Override
	public List<FavoriteDto> getBoardFav(String userId, Integer category) {
		return FavMapper.getBoardFav(userId, category);
	}


	@Override
	public void registBoardFav(Map<String, Object> map) {
		FavMapper.registBoardFav(map);
	}


	@Override
	public void deleteBoardFav(String userId,Integer boardNo) {
		FavMapper.deleteBoardFav(userId,boardNo);
	}


	@Override
	public int getAttrFavCount(Integer contentId, Integer category) {
		return FavMapper.getAttrFavCount(contentId,category);
	}


	@Override
	public int getBoardFavCount(Integer contentId, Integer category) {
		return FavMapper.getBoardFavCount(contentId,category);
	}

}
