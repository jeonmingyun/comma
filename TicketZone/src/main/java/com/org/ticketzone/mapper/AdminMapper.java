package com.org.ticketzone.mapper;

import java.util.ArrayList;

import com.org.ticketzone.domain.AdminVO;

public interface AdminMapper {

	public ArrayList<AdminVO> adminLogin(String admin_id);
}
