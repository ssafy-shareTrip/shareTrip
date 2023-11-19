package com.orj.sharetrip.map.model.service;

import java.util.List;
import java.util.Map;

import com.orj.sharetrip.map.model.AttractionDto;
import com.orj.sharetrip.map.model.SidoGugunCodeDto;

public interface MapService {

	List<SidoGugunCodeDto> getSido() throws Exception;

	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;

	List<AttractionDto> getAttrList(String sidoCode, String gugunCode, String contentTypeId, String keyword);

	AttractionDto getAttrInfo(String contentId);

}
