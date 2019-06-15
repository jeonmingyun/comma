package com.org.ticketzone.service;

import java.util.ArrayList;

import com.org.ticketzone.domain.CategorieVO;
import com.org.ticketzone.domain.MemberVO;
import com.org.ticketzone.domain.NumberTicketVO;
import com.org.ticketzone.domain.OwnerVO;
import com.org.ticketzone.domain.StoreMenuVO;
import com.org.ticketzone.domain.StoreVO;
import com.org.ticketzone.domain.TokenVO;

public interface AppMngrService {
	public OwnerVO[] ownerList();

	public MemberVO[] memberList();

	public StoreVO[] storeList(String owner_id);

	public StoreMenuVO[] menuList();

	public CategorieVO[] categorieList();

	public NumberTicketVO[] NumberTicketList();

	public void M_issue_ticket(NumberTicketVO vo);

	public String M_codeSelect();

	public void M_firstCode();

	public void M_makeTicket(NumberTicketVO vo);

	public void M_plusTicket(NumberTicketVO vo);

	public NumberTicketVO[] M_chart();

	public void success_ticket(NumberTicketVO vo);

	public void success_status(NumberTicketVO vo);

	public void update_time(StoreVO store);

	public void store_max(StoreVO store);

	public void store_enable(StoreVO store);

	public void store_intro(StoreVO store);
	
	public ArrayList<TokenVO> getToken(String member_id);
	
	public ArrayList<StoreVO> getStore(String license_number);
	
}
