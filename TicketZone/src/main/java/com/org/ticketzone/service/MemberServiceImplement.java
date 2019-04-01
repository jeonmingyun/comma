package com.org.ticketzone.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.MemberVO;
import com.org.ticketzone.mapper.MemberMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberServiceImplement implements MemberService {

	private MemberMapper mapper;

	@Override
	public ArrayList<MemberVO> getList() {

		return mapper.getList();
	}

}
