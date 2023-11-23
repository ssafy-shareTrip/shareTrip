package com.orj.sharetrip.user.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "UserDto : 회원 정보", description = "회원의 상세 정보를 나타낸다.")
public class UserDto {

	@ApiModelProperty(value = "아이디")
	private String id;
	@ApiModelProperty(value = "이름")
	private String name;
	@ApiModelProperty(value = "비밀번호")
	private String password;
	@ApiModelProperty(value = "이메일 아이디")
	private String emailId;
	@ApiModelProperty(value = "이메일 도메인")
	private String emailDomain;
	@ApiModelProperty(value = "가입일")
	private String joinDate;
	@ApiModelProperty(value = "태어난 연도")
	private String birth;
	@ApiModelProperty(value = "성별")
	private String gender;
	@ApiModelProperty(value = "프로필 이미지")
	private String profileImage;
	@ApiModelProperty(value = "refreshToken")
	private String token;
	@ApiModelProperty(value = "팔로잉")
	private Integer following;
	@ApiModelProperty(value = "팔로잉 리스트")
	private List<UserDto> followingList;
	@ApiModelProperty(value = "팔로워")
	private Integer follower;
	@ApiModelProperty(value = "팔로워 리스트")
	private List<UserDto> followerList;
	
	
}

//password varchar(45) 
//name varchar(45) 
//email_id varchar(45) 
//email_domain varchar(45) 
//join_date datetime 
//birth date 
//gender varchar(20) 
//profile_image longtext 
//token
