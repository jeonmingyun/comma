package com.org.ticketzone.mapper;

import java.util.ArrayList;

import com.org.ticketzone.domain.CoordinatesVO;

public interface CoordinatesMapper {
	public void insertXY(CoordinatesVO coor);
	public ArrayList<CoordinatesVO> XYList(String license_number);
}
