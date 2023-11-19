package com.orj.sharetrip.user.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.orj.sharetrip.user.model.UserDto;
import com.orj.sharetrip.user.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	private UserMapper UserMapper;

	public UserServiceImpl(UserMapper UserMapper) {
		super();
		this.UserMapper = UserMapper;
	}

	@Override
	public UserDto login(UserDto UserDto) throws Exception {
		return UserMapper.login(UserDto);
	}
	
	@Override
	public UserDto userInfo(String id) throws Exception {
		return UserMapper.userInfo(id);
	}

	@Override
	public void saveRefreshToken(String id, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("token", refreshToken);
		UserMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String id) throws Exception {
		return UserMapper.getRefreshToken(id);
	}

	@Override
	public void deleRefreshToken(String id) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("token", null);
		UserMapper.deleteRefreshToken(map);
	}

	@Override
	public void joinUser(UserDto UserDto) throws Exception {
		UserMapper.joinUser(UserDto);
	}

}
