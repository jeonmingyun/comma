package com.org.ticketzone.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.AdminVO;
import com.org.ticketzone.mapper.AdminMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminServiceImplement implements AdminService {
	private AdminMapper mapper;
	@Override
	public ArrayList<AdminVO> adminLogin(String admin_id) {

		return mapper.adminLogin(admin_id);
	}

}
