package com.org.ticketzone.service;

import java.util.ArrayList;

import com.org.ticketzone.domain.BeaconVO;
import com.org.ticketzone.domain.CategorieVO;
import com.org.ticketzone.domain.CoordinatesVO;
import com.org.ticketzone.domain.NumberTicketVO;
import com.org.ticketzone.domain.OwnerVO;
import com.org.ticketzone.domain.StoreMenuVO;
import com.org.ticketzone.domain.StoreVO;
import com.org.ticketzone.domain.TokenVO;

public interface AppMemService {
	public OwnerVO[] ownerList();
	public StoreVO[] storeList();
	public StoreMenuVO[] menuList();
	public CategorieVO[] categorieList();
	public CoordinatesVO[] coordinatesList();
	public NumberTicketVO[] NumberTicketList();
	public BeaconVO[] BeaconList();
	public void insertId(TokenVO token);
	public CoordinatesVO[] gpsTest(CoordinatesVO vo);
	public ArrayList<TokenVO> tokenList(String token_id);
 	public String Mem_codeSelect(NumberTicketVO vo);
	public void Mem_firstCode(NumberTicketVO vo);
	public void Mem_makeTicket(NumberTicketVO vo);
	public void Mem_plusTicket(NumberTicketVO vo);
	public NumberTicketVO[] MyTicket(NumberTicketVO vo);
	public void TicketCancel(NumberTicketVO vo);
	public void SyncTicket(NumberTicketVO vo);
	public void insertToken(String token_id);

}