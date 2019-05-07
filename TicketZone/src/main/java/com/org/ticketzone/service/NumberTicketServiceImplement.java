
package com.org.ticketzone.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.ChartVO;
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

	@Override
	public ArrayList<NumberTicketVO> tWait(String license_number) {

		return mapper.tWait(license_number);
	}

	@Override
	public ArrayList<NumberTicketVO> tSuccess(String license_number) {

		return mapper.tSuccess(license_number);
	}

	@Override
	public ArrayList<NumberTicketVO> tCancel(String license_number) {

		return mapper.tCancel(license_number);
	}

	@Override
	public ArrayList<NumberTicketVO> tAbsence(String license_number) {

		return mapper.tAbsence(license_number);
	}

	@Override
	public void cancelStatus(NumberTicketVO ticket) {

		mapper.cancelStatus(ticket);

	}

	@Override
	public void absenceStatus(NumberTicketVO ticket) {

		mapper.absenceStatus(ticket);

	}

	@Override
	public void cancelTicket(NumberTicketVO ticket) {

		mapper.cancelTicket(ticket);

	}

	@Override
	public ArrayList<NumberTicketVO> getTotal() {
		
		return mapper.getTotal();
	}

	@Override
	public ArrayList<NumberTicketVO> today() {
		
		return mapper.today();
	}

	@Override
	public ArrayList<NumberTicketVO> todayDel(String today) {
		
		return mapper.todayDel(today);
	}

	@Override
	public ArrayList<NumberTicketVO> todayAdd(String today) {

		return mapper.todayAdd(today);
	}

	@Override
	public ArrayList<NumberTicketVO> getTotalAdd(String today) {

		return mapper.getTotalAdd(today);
	}

	@Override
	public ArrayList<NumberTicketVO> getTotalDel(String today) {

		return mapper.getTotalDel(today);
	}

	
}
