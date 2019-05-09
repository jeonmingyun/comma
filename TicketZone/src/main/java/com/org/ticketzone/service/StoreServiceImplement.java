package com.org.ticketzone.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.StoreCriteria;
import com.org.ticketzone.domain.StoreVO;
import com.org.ticketzone.mapper.StoreMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StoreServiceImplement implements StoreService{
	
	private StoreMapper mapper;
	
	@Override
	public ArrayList<StoreVO> storeList(StoreCriteria cri) {
		
		return mapper.getListWithPaging(cri);
	}
	
	@Override
	public ArrayList<StoreVO> getListWithPaging(StoreCriteria cri) {
		// TODO Auto-generated method stub
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int total(StoreCriteria cri) {
		
		return mapper.total(cri);
	}
	
	@Override
	public int SearchCount(StoreCriteria cri) {
		
		return mapper.SearchCount(cri);
	}

	@Override
	public ArrayList<StoreVO> storeInfo(String license) {
		
		return mapper.storeInfo(license);
	}

	@Override
	public void storeRegister(StoreVO store) {
		mapper.storeRegister(store);
		
	}

	@Override
	public ArrayList<StoreVO> storeGet(String id) {
		
		return mapper.storeGet(id);
	}

	@Override
	public StoreVO storeUpdate(String license) {
	
		return mapper.storeUpdate(license);
	}

	@Override
	public void storeUpdCom(StoreVO store) {
		
		mapper.storeUpdCom(store);
		
	}

	@Override
	public ArrayList<StoreVO> getListWithSearchPaging(StoreCriteria cri) {
		// TODO Auto-generated method stub
		return mapper.getListWithSearchPaging(cri);
	}

	@Override
	public int searchTotal(StoreVO store) {
		// TODO Auto-generated method stub
		return mapper.searchTotal(store);
	}

}
