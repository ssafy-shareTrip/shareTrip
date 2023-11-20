package com.orj.sharetrip.fav.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "FavoriteDto : 좋아요 북마크 정보", description = "유저의 좋아요 또는 북마크에 대한 정보를 나타낸다.")
public class FavoriteDto {
	private Object favId;
	private int category;
}
