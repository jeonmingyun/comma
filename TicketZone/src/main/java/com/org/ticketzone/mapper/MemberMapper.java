package com.org.ticketzone.mapper;

import java.util.ArrayList;

import com.org.ticketzone.domain.MemberVO;

public interface MemberMapper {
	public ArrayList<MemberVO> getList();
	public ArrayList<MemberVO> memberTest();
}
