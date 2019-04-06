package com.org.ticketzone.service;

import java.util.ArrayList;

import com.org.ticketzone.domain.NumberTicketVO;

public interface NumberTicketService {
	public String codeSelect(NumberTicketVO ticket);
	public void firstCode(NumberTicketVO ticket);
	public void makeTicket(NumberTicketVO ticket);
	public void plusTicket(NumberTicketVO ticket);
	public void minusTicket(NumberTicketVO ticket);
	public ArrayList<NumberTicketVO> waitList(String license_number);
}
