package com.orj.sharetrip.fav.model.service;

import java.util.List;
import java.util.Map;

import com.orj.sharetrip.fav.model.FavoriteDto;

public interface FavService {
	List<FavoriteDto> getAttrFav(String userId, Integer category);

	void deleteAttrFav(Map<String, Object> map);

	void registAttrFav(Map<String, Object> map);
}
