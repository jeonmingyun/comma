package com.org.ticketzone.service;

import com.org.ticketzone.domain.MemberVO;

public interface InterceptorService {
	public MemberVO member_login(String admin_id, String admin_password);
}
