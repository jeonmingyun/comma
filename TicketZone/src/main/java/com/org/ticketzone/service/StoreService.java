package com.org.ticketzone.service;

import java.util.ArrayList;

import com.org.ticketzone.domain.StoreVO;

public interface StoreService {
	public ArrayList<StoreVO> storeList();
	public ArrayList<StoreVO> storeInfo(String license);
}
