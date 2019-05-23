package com.org.ticketzone.service;

import java.util.ArrayList;



import com.org.ticketzone.domain.CoordinatesVO;



public interface CoordinatesService {
	public void insertXY(CoordinatesVO coor);

	public ArrayList<CoordinatesVO> XYList(String license_number);
}
