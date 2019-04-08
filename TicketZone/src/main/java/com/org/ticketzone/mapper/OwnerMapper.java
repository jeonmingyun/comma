package com.org.ticketzone.mapper;

import java.util.ArrayList;

import com.org.ticketzone.domain.OwnerVO;

public interface OwnerMapper {
	public ArrayList<OwnerVO> login(String id);
	
	public void insertOwner(OwnerVO owner);
}
