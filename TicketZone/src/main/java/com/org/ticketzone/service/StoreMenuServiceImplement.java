package com.org.ticketzone.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.StoreMenuVO;
import com.org.ticketzone.mapper.StoreMenuMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StoreMenuServiceImplement implements StoreMenuService {

	private StoreMenuMapper mapper;
	
	@Override
	public ArrayList<StoreMenuVO> menuList(String license_number) {
		
		return mapper.menuList(license_number);
	}

	@Override
	public ArrayList<StoreMenuVO> getCate(StoreMenuVO menu) {
		
		return mapper.getCate(menu);
	}

	@Override
	public ArrayList<StoreMenuVO> getListTocate(StoreMenuVO menu) {
		
		return mapper.getListTocate(menu);
	}

	@Override
	public String checkMenu(String license_number) {
		
		return mapper.checkMenu(license_number);
	}

	@Override
	public void addMenu(ArrayList<StoreMenuVO> menu) {
		
		for(StoreMenuVO vo:menu) {
			mapper.addMenu(vo);
		}		
	}

	@Override
	public void firstMenu(ArrayList<StoreMenuVO> menu) {
		
		for(StoreMenuVO vo : menu) {
		mapper.firstMenu(vo);
				
		}
		
	}


	

}
