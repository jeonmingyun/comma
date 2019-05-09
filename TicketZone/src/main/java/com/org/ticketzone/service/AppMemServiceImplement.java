package com.org.ticketzone.service;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.CategorieVO;
import com.org.ticketzone.domain.CoordinatesVO;
import com.org.ticketzone.domain.OwnerVO;
import com.org.ticketzone.domain.StoreMenuVO;
import com.org.ticketzone.domain.StoreVO;
import com.org.ticketzone.mapper.AppMemMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AppMemServiceImplement implements AppMemService {
	AppMemMapper mapper;	
	
	@Override
	public OwnerVO[] ownerList() {
		return mapper.ownerList();
	}

	@Override
	public StoreVO[] storeList() {
		return mapper.storeList();
	}

	@Override
	public StoreMenuVO[] menuList() {
		return mapper.menuList();
	}

	@Override
	public CategorieVO[] categorieList() {
		return mapper.categorieList();
	}

	@Override
	public CoordinatesVO[] coordinatesList() {
		return mapper.coordinatesList();
	}

}
