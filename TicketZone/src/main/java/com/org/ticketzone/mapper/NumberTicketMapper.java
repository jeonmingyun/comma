package com.org.ticketzone.mapper;

import com.org.ticketzone.domain.NumberTicketVO;

public interface NumberTicketMapper {
	public String codeSelect(NumberTicketVO ticket);
	public void firstCode(NumberTicketVO ticket);
	public void makeTicket(NumberTicketVO ticket);
	public void plusTicket(NumberTicketVO ticket);
	public void minusTicket(NumberTicketVO ticket);
}
