package com.orj.sharetrip.map.model;

import java.util.List;

import com.orj.sharetrip.memo.model.MemoDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "AttractionDto : 관광지정보", description = "관광지의 상세 정보를 나타낸다.")
public class AttractionDto {

	
	@ApiModelProperty(value = "관광지 ID")
	private int contentId;
	@ApiModelProperty(value = "관광타입(12:관광지, 14:문화시설, 15:축제공연행사, 25:여행코스, 28:레포츠, 32:숙박, 38:쇼핑, 39:음식점) ID")
	private int contentTypeId;
	@ApiModelProperty(value = "관광지 이름")
	private String title;
	@ApiModelProperty(value = "주소1")
	private String addr1;
	@ApiModelProperty(value = "주소2")
	private String addr2;
	@ApiModelProperty(value = "전화번호")
	private String tel;
	@ApiModelProperty(value = "이미지 경로")
	private String firstImage;
	@ApiModelProperty(value = "이미지 경로2")
	private String firstImage2;
	@ApiModelProperty(value = "시도코드")
	private int sidoCode;
	@ApiModelProperty(value = "구군코드")
	private int gugunCode;
	@ApiModelProperty(value = "위도")
	private double latitude;
	@ApiModelProperty(value = "경도")
	private double longitude;
	@ApiModelProperty(value = "관굉지에 대한 설명")
	private String overview;
	@ApiModelProperty(value = "댓글")
	private List<MemoDto> memos;
	@ApiModelProperty(value = "유저의 좋아요 판단")
	private Integer isLike;
	@ApiModelProperty(value = "총 좋아요 개수")
	private Integer totalLike;
	@ApiModelProperty(value = "유저의 북마크 판단")
	private Integer isBookmark;
	@ApiModelProperty(value = "총 북마크 개수")
	private Integer totalBookmark;
	
}
