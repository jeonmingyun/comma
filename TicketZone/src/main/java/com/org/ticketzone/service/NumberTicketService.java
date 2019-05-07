
package com.org.ticketzone.service;

import java.util.ArrayList;

import com.org.ticketzone.domain.ChartVO;
import com.org.ticketzone.domain.NumberTicketVO;

public interface NumberTicketService {
	public String codeSelect(NumberTicketVO ticket);

	public void firstCode(NumberTicketVO ticket);

	public void makeTicket(NumberTicketVO ticket);

	public void plusTicket(NumberTicketVO ticket);

	public void minusTicket(NumberTicketVO ticket);

	public ArrayList<NumberTicketVO> waitList(String license_number);

	public ArrayList<NumberTicketVO> tWait(String license_number);

	public ArrayList<NumberTicketVO> tSuccess(String license_number);

	public ArrayList<NumberTicketVO> tCancel(String license_number);

	public ArrayList<NumberTicketVO> tAbsence(String license_number);

	public void cancelStatus(NumberTicketVO ticket);

	public void absenceStatus(NumberTicketVO ticket);

	public void cancelTicket(NumberTicketVO ticket);
	
	public ArrayList<NumberTicketVO> getTotal();
	
	public ArrayList<NumberTicketVO> today();
	
	public ArrayList<NumberTicketVO> todayDel(String today);
	
	public ArrayList<NumberTicketVO> todayAdd(String today);
	
	public ArrayList<NumberTicketVO> getTotalAdd(String today);
	
	public ArrayList<NumberTicketVO> getTotalDel(String today);
	
}
