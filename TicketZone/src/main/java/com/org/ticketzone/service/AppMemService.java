package com.org.ticketzone.service;

import com.org.ticketzone.domain.BeaconVO;
import com.org.ticketzone.domain.CategorieVO;
import com.org.ticketzone.domain.CoordinatesVO;
import com.org.ticketzone.domain.NumberTicketVO;
import com.org.ticketzone.domain.OwnerVO;
import com.org.ticketzone.domain.StoreMenuVO;
import com.org.ticketzone.domain.StoreVO;

public interface AppMemService {
	public OwnerVO[] ownerList();
	public StoreVO[] storeList();
	public StoreMenuVO[] menuList();
	public CategorieVO[] categorieList();
	public CoordinatesVO[] coordinatesList();
	public NumberTicketVO[] NumberTicketList();
	public BeaconVO[] BeaconList();
	
	public CoordinatesVO[] gpsTest(CoordinatesVO vo);
	
 	public String Mem_codeSelect(NumberTicketVO vo);
	public void Mem_firstCode(NumberTicketVO vo);
	public void Mem_makeTicket(NumberTicketVO vo);
	public void Mem_plusTicket(NumberTicketVO vo);
	public NumberTicketVO[] MyTicket(NumberTicketVO vo);
	public void TicketCancel(NumberTicketVO vo);
	public void SyncTicket(NumberTicketVO vo);

}