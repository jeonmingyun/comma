package com.org.ticketzone.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
//4월2일 미완성 코드만들어주는VO
public class TicketCode {
	private String ticketcode;
	
	public String CodeMake() {
				
		return ticketcode;
	}
	
}
