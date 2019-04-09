package com.org.ticketzone.service;

import java.util.ArrayList;

import com.org.ticketzone.domain.OwnerVO;

public interface OwnerService {
	public ArrayList<OwnerVO> login(String id);
	public void insertOwner(OwnerVO owner);
	public ArrayList<OwnerVO> findOwner(String owner_name, String owner_tel);
}
