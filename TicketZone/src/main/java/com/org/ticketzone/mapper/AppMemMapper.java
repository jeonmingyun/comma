package com.org.ticketzone.mapper;

import com.org.ticketzone.domain.CategorieVO;
import com.org.ticketzone.domain.CoordinatesVO;
import com.org.ticketzone.domain.NumberTicketVO;
import com.org.ticketzone.domain.OwnerVO;
import com.org.ticketzone.domain.StoreMenuVO;
import com.org.ticketzone.domain.StoreVO;

public interface AppMemMapper {
	public OwnerVO[] ownerList();
	public StoreVO[] storeList();
	public StoreMenuVO[] menuList();
	public CategorieVO[] categorieList();
	public CoordinatesVO[] coordinatesList();
}
