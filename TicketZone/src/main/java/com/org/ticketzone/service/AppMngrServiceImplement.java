package com.org.ticketzone.service;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.CategorieVO;
import com.org.ticketzone.domain.MemberVO;
import com.org.ticketzone.domain.OwnerVO;
import com.org.ticketzone.domain.StoreMenuVO;
import com.org.ticketzone.domain.StoreVO;
import com.org.ticketzone.mapper.AppMngrMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppMngrServiceImplement implements AppMngrService {
	AppMngrMapper mapper;
	
	@Override	
	public OwnerVO[] ownerList() {
		// TODO Auto-generated method stub
		return mapper.ownerList();
	}

	@Override
	public MemberVO[] memberList() {
		// TODO Auto-generated method stub
		return mapper.memberList();
	}

	@Override
	public StoreVO[] storeList() {
		// TODO Auto-generated method stub
		return mapper.storeList();
	}

	@Override
	public StoreMenuVO[] menuList() {
		// TODO Auto-generated method stub
		return mapper.menuList();
	}

	@Override
	public CategorieVO[] categorieList() {
		// TODO Auto-generated method stub
		return mapper.categorieList();
	}

}
