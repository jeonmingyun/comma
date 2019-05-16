package com.org.ticketzone.service;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.CategorieVO;
import com.org.ticketzone.domain.MemberVO;
import com.org.ticketzone.domain.NumberTicketVO;
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
		return mapper.ownerList();
	}

	@Override
	public MemberVO[] memberList() {
		return mapper.memberList();
	}

	@Override
	public StoreVO[] storeList(String owner_id) {
		return mapper.storeList(owner_id);
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
	public NumberTicketVO[] ticketList() {
		return mapper.ticketList();
	}

}
