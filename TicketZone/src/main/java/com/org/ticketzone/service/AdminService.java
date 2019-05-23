package com.org.ticketzone.service;

import java.util.ArrayList;

import com.org.ticketzone.domain.AdminVO;

public interface AdminService {
	public ArrayList<AdminVO> adminLogin(String admin_id);
}
