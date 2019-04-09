package com.org.ticketzone.service;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.MemberVO;
import com.org.ticketzone.mapper.InterceptorMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InterfaceServiceImplement implements InterceptorService {
	InterceptorMapper mapper;
	
	@Override
	public MemberVO member_login(String admin_id, String admin_password) {
		return mapper.member_login(admin_id, admin_password);
	}

}
