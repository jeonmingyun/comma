package com.org.ticketzone.mapper;

import java.util.ArrayList;

import com.org.ticketzone.domain.StoreMenuVO;

public interface StoreMenuMapper {
	public ArrayList<StoreMenuVO> menuList(String license_number);
	public ArrayList<StoreMenuVO> getCate(StoreMenuVO menu);
	public ArrayList<StoreMenuVO> getListTocate(StoreMenuVO menu);
	public String checkMenu(String license_number);
	public void addMenu(StoreMenuVO menu);
	public void firstMenu(StoreMenuVO menu);
	public void updateMenu(StoreMenuVO menu);
	public void deleteMenu(StoreMenuVO menu);
}
