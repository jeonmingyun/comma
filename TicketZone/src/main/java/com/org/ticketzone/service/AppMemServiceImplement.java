package com.org.ticketzone.service;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.CategorieVO;
import com.org.ticketzone.domain.CoordinatesVO;
import com.org.ticketzone.domain.NumberTicketVO;
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

<<<<<<< HEAD
	@Override
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

}
=======
}
>>>>>>> 053a7c1c022cbcfbc74a35f156e50aae72e5c814
