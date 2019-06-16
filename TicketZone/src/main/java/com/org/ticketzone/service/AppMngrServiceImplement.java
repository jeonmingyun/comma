package com.org.ticketzone.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.CategorieVO;
import com.org.ticketzone.domain.MemberVO;
import com.org.ticketzone.domain.NumberTicketVO;
import com.org.ticketzone.domain.OwnerVO;
import com.org.ticketzone.domain.StoreMenuVO;
import com.org.ticketzone.domain.StoreVO;
import com.org.ticketzone.domain.TokenVO;
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
	public NumberTicketVO[] NumberTicketList() {
		return mapper.NumberTicketList();
	}

	@Override
	public void M_issue_ticket(NumberTicketVO vo) {
		mapper.M_issue_ticket(vo);

	}

	@Override
	public String M_codeSelect() {
		return mapper.M_codeSelect();
	}

	@Override
	public void M_firstCode() {
		mapper.M_firstCode();

	}

	@Override
	public void M_makeTicket(NumberTicketVO vo) {
		mapper.M_makeTicket(vo);
	}

	@Override
	public void M_plusTicket(NumberTicketVO vo) {
		mapper.M_plusTicket(vo);
	}

	@Override
	public NumberTicketVO[] M_chart() {
		// TODO Auto-generated method stub
		return mapper.M_chart();
	}

	@Override

	public void success_ticket(NumberTicketVO vo) {
		mapper.success_ticket(vo);
	}

	public void update_time(StoreVO store) {
		mapper.update_time(store);
	}

	@Override
	public void store_max(StoreVO store) {
		mapper.store_max(store);

	}

	@Override

	public void success_status(NumberTicketVO vo) {
		mapper.success_status(vo);
	}

	public void store_enable(StoreVO store) {
		mapper.store_enable(store);

	}

	@Override
	public void store_intro(StoreVO store) {
		mapper.store_intro(store);
	}
	
	public ArrayList<TokenVO> getToken(String member_id) {
		// TODO Auto-generated method stub
		return mapper.getToken(member_id);
	}

	@Override
	public ArrayList<StoreVO> getStore(String license_number) {
		// TODO Auto-generated method stub
		return mapper.getStore(license_number);
	}

	@Override
	public NumberTicketVO[] RefreshList(NumberTicketVO vo) {
		// TODO Auto-generated method stub
		return mapper.RefreshList(vo);
	}

}
