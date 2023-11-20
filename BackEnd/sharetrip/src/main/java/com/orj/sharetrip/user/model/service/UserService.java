package com.orj.sharetrip.user.model.service;

import java.util.Map;

import com.orj.sharetrip.user.model.UserDto;

public interface UserService {
	
	UserDto login(UserDto UserDto) throws Exception;
	void joinUser(UserDto UserDto) throws Exception;
	UserDto userInfo(String userId) throws Exception;
	void saveRefreshToken(String userId, String refreshToken) throws Exception;
	Object getRefreshToken(String userId) throws Exception;
	void deleRefreshToken(String userId) throws Exception;
	void followUser(Map<String,Object> map);
	void unFollowUser(Map<String,Object> map);
	void modifyUser(UserDto userDto);
	void deleteUser(UserDto userDto);
	
}
