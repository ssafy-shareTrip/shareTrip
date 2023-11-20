package com.orj.sharetrip.map.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.orj.sharetrip.map.model.AttractionDto;
import com.orj.sharetrip.map.model.SidoGugunCodeDto;
import com.orj.sharetrip.map.model.mapper.MapMapper;

@Service
public class MapServiceImpl implements MapService {

	private MapMapper MapMapper;

	public MapServiceImpl(MapMapper mapMapper) {
		super();
		this.MapMapper = mapMapper;
	}

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return MapMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return MapMapper.getGugunInSido(sido);
	}

	@Override
	public List<AttractionDto> getAttrList(Map<String, Object> map) {
		return MapMapper.getAttrList(map);
	}

	@Override
	public AttractionDto getAttrInfo(String contentId) {
		return MapMapper.getAttrInfo(contentId);
	}

}
