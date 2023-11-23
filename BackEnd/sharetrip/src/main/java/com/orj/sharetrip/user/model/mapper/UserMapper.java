package com.orj.sharetrip.user.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.orj.sharetrip.user.model.UserDto;

@Mapper
public interface UserMapper {
	UserDto login(UserDto UserDto) ;
	UserDto userInfo(String userId);
	void saveRefreshToken(Map<String, String> map) ;
	Object getRefreshToken(String userid) ;
	void deleteRefreshToken(Map<String, String> map);
	void joinUser(UserDto userDto);
	void followUser(String idTo,String userId);
	void unFollowUser(String idTo,String userId);
	void modifyUser(UserDto userDto);
	void deleteUser(UserDto userDto);
	List<UserDto> bothFollow(String userId);
	
}
