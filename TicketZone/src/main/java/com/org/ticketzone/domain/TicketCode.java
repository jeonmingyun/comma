package com.org.ticketzone.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
//TicketCode를 만들어주는 코드
public class TicketCode {
	private String Makecode;
	private int code;
	
	public int makeCode(String Makecode) {
		code = Integer.parseInt(Makecode);
		 return code;
	}
	
}
