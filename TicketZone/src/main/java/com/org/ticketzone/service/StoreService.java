package com.org.ticketzone.service;

import java.util.ArrayList;

import com.org.ticketzone.domain.StoreCriteria;
import com.org.ticketzone.domain.StoreVO;

public interface StoreService {
	public ArrayList<StoreVO> storeList(StoreCriteria cri);

	public ArrayList<StoreVO> storeInfo(String license);

	public void storeRegister(StoreVO store);

	public ArrayList<StoreVO> storeGet(String id);

	//	매장수정페이지
	public StoreVO storeUpdate(String license);

	// 매장 수정 처리(update)
	public void storeUpdCom(StoreVO store);
	
	public ArrayList<StoreVO> getListWithPaging(StoreCriteria cri);
	public int total(StoreCriteria cri);
	public int SearchCount(StoreCriteria cri);
}
