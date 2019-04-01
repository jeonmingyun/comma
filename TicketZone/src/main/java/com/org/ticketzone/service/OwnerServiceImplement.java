package com.org.ticketzone.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.OwnerVO;
import com.org.ticketzone.mapper.OwnerMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OwnerServiceImplement implements OwnerService {

	private OwnerMapper mapper;
	
	@Override
	public ArrayList<OwnerVO> login(String id) {
		
		return mapper.login(id);
	}

	@Override
	public void insertOwner(OwnerVO owner) {
		mapper.insertOwner(owner);
		
	}

}
