package com.orj.sharetrip.fav.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.orj.sharetrip.fav.model.FavoriteDto;

@Mapper
public interface FavMapper {
	List<FavoriteDto> getAttrFav(String userId, Integer category);

	void deleteAttrFav(Map<String, Object> map);

	void registAttrFav(Map<String, Object> map); 
}
