package com.orj.sharetrip.map.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.orj.sharetrip.map.model.AttractionDto;
import com.orj.sharetrip.map.model.SidoGugunCodeDto;
import com.orj.sharetrip.map.model.StatDto;

public interface MapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;

	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;

	List<AttractionDto> getAttrList(Map<String, Object> map);

	AttractionDto getAttrInfo(String contentId,String userId);

	StatDto getStat();

}
