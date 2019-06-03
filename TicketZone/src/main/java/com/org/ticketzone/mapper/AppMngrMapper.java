package com.org.ticketzone.mapper;

import com.org.ticketzone.domain.CategorieVO;
import com.org.ticketzone.domain.MemberVO;
import com.org.ticketzone.domain.NumberTicketVO;
import com.org.ticketzone.domain.OwnerVO;
import com.org.ticketzone.domain.StoreMenuVO;
import com.org.ticketzone.domain.StoreVO;

public interface AppMngrMapper {
	public OwnerVO[] ownerList();
	public MemberVO[] memberList();
	public StoreVO[] storeList(String owner_id);
	public StoreMenuVO[] menuList();
	public CategorieVO[] categorieList();
	public NumberTicketVO[] ticketList();
	public void M_issue_ticket(NumberTicketVO vo);
	public String M_codeSelect();
	public void M_firstCode();
	public void M_makeTicket(NumberTicketVO vo);
	public void M_plusTicket(NumberTicketVO vo);
	public NumberTicketVO[] M_chart();
}
