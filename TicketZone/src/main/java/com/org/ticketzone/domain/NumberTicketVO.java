package com.org.ticketzone.domain;

import lombok.Data;

@Data
public class NumberTicketVO {
	private String ticket_code; //번호표 코드
	private int wait_number; //대기 인원 수
	private int the_number; // 입장 인원 수
	private String license_number;
	private String member_tel;
	private int ticket_status;
}
