package com.org.ticketzone.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.BeaconVO;
import com.org.ticketzone.domain.CategorieVO;
import com.org.ticketzone.domain.CoordinatesVO;
import com.org.ticketzone.domain.NumberTicketVO;
import com.org.ticketzone.domain.OwnerVO;
import com.org.ticketzone.domain.StoreMenuVO;
import com.org.ticketzone.domain.StoreVO;
import com.org.ticketzone.domain.TokenVO;
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

	@Override
	public String Mem_codeSelect(NumberTicketVO vo) {
		return mapper.Mem_codeSelect(vo);
	}

	@Override
	public void Mem_firstCode(NumberTicketVO vo) {
		mapper.Mem_firstCode(vo);
	}

	@Override
	public void Mem_makeTicket(NumberTicketVO vo) {
		mapper.Mem_makeTicket(vo);
	}

	@Override
	public void Mem_plusTicket(NumberTicketVO vo) {
		mapper.Mem_plusTicket(vo);
	}

	@Override
	public NumberTicketVO[] NumberTicketList() {
		return mapper.NumberTicketList();
	}

	@Override
	public BeaconVO[] BeaconList() {
		return mapper.BeaconList();
	}

	@Override
	public CoordinatesVO[] gpsTest(CoordinatesVO vo) {
		return mapper.gpsTest(vo);
	}

	public NumberTicketVO[] MyTicket(NumberTicketVO vo) {
		return mapper.MyTicket(vo);
	}

	@Override
	public void TicketCancel(NumberTicketVO vo) {
		mapper.TicketCancel(vo);
	}

	@Override
	public void SyncTicket(NumberTicketVO vo) {
		mapper.SyncTicket(vo);		

	}

	@Override
	public void insertToken(String token_id) {
		mapper.insertToken(token_id);
		
	}

	@Override
	public ArrayList<TokenVO> tokenList(String token_id) {
		// TODO Auto-generated method stub
		return mapper.tokenList(token_id);
	}

	@Override
	public void insertId(TokenVO token) {
			mapper.insertId(token);
	
	}

}
