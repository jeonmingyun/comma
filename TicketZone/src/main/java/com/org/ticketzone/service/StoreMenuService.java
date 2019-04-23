package com.org.ticketzone.service;

import java.util.ArrayList;

import com.org.ticketzone.domain.StoreMenuVO;

public interface StoreMenuService {
	public ArrayList<StoreMenuVO> menuList(String license_number);
	public ArrayList<StoreMenuVO> getCate(StoreMenuVO menu);
	public ArrayList<StoreMenuVO> getListTocate(StoreMenuVO menu);
	public String checkMenu(String license_number);
	public void addMenu(ArrayList<StoreMenuVO> menu);
	public void firstMenu(ArrayList<StoreMenuVO> menu);
	
}
