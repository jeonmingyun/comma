package com.org.ticketzone.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.NumberTicketVO;
import com.org.ticketzone.mapper.NumberTicketMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NumberTicketServiceImplement implements NumberTicketService {

	private NumberTicketMapper mapper;

	@Override
	public String codeSelect(NumberTicketVO ticket) {

		return mapper.codeSelect(ticket);
	}

	@Override
	public void firstCode(NumberTicketVO ticket) {
		mapper.firstCode(ticket);
		
	}

	@Override
	public void makeTicket(NumberTicketVO ticket) {
		mapper.makeTicket(ticket);
		
	}

	@Override
	public void plusTicket(NumberTicketVO ticket) {
		mapper.plusTicket(ticket);
		
	}

	@Override
	public void minusTicket(NumberTicketVO ticket) {
		mapper.minusTicket(ticket);
		
	}

	@Override
	public ArrayList<NumberTicketVO> waitList(String license_number) {
		
		return mapper.waitList(license_number);
	}

}
