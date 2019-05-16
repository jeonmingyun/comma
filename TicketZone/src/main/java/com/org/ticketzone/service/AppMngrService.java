package com.org.ticketzone.service;

import com.org.ticketzone.domain.CategorieVO;
import com.org.ticketzone.domain.MemberVO;
import com.org.ticketzone.domain.NumberTicketVO;
import com.org.ticketzone.domain.OwnerVO;
import com.org.ticketzone.domain.StoreMenuVO;
import com.org.ticketzone.domain.StoreVO;

public interface AppMngrService {
	public OwnerVO[] ownerList();
	public MemberVO[] memberList();
	public StoreVO[] storeList(String owner_id);
	public StoreMenuVO[] menuList();
	public CategorieVO[] categorieList();
	public NumberTicketVO[] ticketList();
}
