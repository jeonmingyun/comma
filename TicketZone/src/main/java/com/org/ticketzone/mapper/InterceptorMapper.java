package com.org.ticketzone.mapper;

import org.apache.ibatis.annotations.Param;

import com.org.ticketzone.domain.MemberVO;

public interface InterceptorMapper {
	public MemberVO member_login(@Param("admin_id") String admin_id, @Param("admin_password") String admin_password);
}