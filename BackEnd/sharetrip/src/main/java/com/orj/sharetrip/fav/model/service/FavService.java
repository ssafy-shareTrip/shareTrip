package com.orj.sharetrip.fav.model.service;

import java.util.List;
import java.util.Map;

import com.orj.sharetrip.fav.model.FavoriteDto;
import com.orj.sharetrip.map.model.AttractionDto;

public interface FavService {
	List<AttractionDto> getAttrFav(String userId, Integer category);

	void deleteAttrFav(String userId, Integer contentId, Integer category);

	void registAttrFav(Map<String, Object> map);

	int getAttrFavCount(Integer contentId, Integer category);

}
