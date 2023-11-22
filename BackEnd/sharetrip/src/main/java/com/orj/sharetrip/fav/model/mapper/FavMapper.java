package com.orj.sharetrip.fav.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.orj.sharetrip.fav.model.FavoriteDto;

@Mapper
public interface FavMapper {
	List<FavoriteDto> getAttrFav(String userId, Integer category);

	void deleteAttrFav(String userId,Integer contentId, Integer category);

	void registAttrFav(Map<String, Object> map);

	List<FavoriteDto> getBoardFav(String userId, Integer category);

	void registBoardFav(Map<String, Object> map);

	void deleteBoardFav(String userId,Integer boardNo, Integer category);

	int getAttrFavCount(Integer contentId, Integer category);

	int getBoardFavCount(Integer contentId, Integer category); 
}
