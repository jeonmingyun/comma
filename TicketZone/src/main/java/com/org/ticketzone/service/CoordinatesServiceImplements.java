package com.org.ticketzone.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.CoordinatesVO;
import com.org.ticketzone.mapper.CoordinatesMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CoordinatesServiceImplements implements CoordinatesService {
	
	private CoordinatesMapper mapper;
	
	@Override
	public void insertXY(CoordinatesVO coor) {
		mapper.insertXY(coor);
		
	}

	@Override
	public ArrayList<CoordinatesVO> XYList(String license_number) {
		
		return mapper.XYList(license_number);
	}

}
