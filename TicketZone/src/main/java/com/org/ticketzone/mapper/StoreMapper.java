package com.org.ticketzone.mapper;

import java.util.ArrayList;

import com.org.ticketzone.domain.StoreVO;

public interface StoreMapper {
	public ArrayList<StoreVO> storeList();
	
	public ArrayList<StoreVO> storeInfo(String license);
	
	public void storeRegister(StoreVO store);
	
	public ArrayList<StoreVO> storeGet(String id);
}
