package com.org.ticketzone.service;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.AddressVO;
import com.org.ticketzone.mapper.AddressMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AddressServiceImplement implements AddressService {
	
	private AddressMapper mapper;
	
	@Override
	public void insertAddress(AddressVO address) {

		mapper.insertAddress(address);
	}

}
