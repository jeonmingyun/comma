package com.org.ticketzone.mapper;

import com.org.ticketzone.domain.CategorieVO;
import com.org.ticketzone.domain.MemberVO;
import com.org.ticketzone.domain.OwnerVO;
import com.org.ticketzone.domain.StoreMenuVO;
import com.org.ticketzone.domain.StoreVO;

public interface AppMngrMapper {
	public OwnerVO[] ownerList();
	public MemberVO[] memberList();
	public StoreVO[] storeList();
	public StoreMenuVO[] menuList();
	public CategorieVO[] categorieList();
}
