
package com.org.ticketzone.domain;

import lombok.Data;

@Data
public class NumberTicketVO {
	private String ticket_code; 
	private int wait_number;
	private int the_number; 
	private String license_number;
	private String member_id;
	private int ticket_status;
	private String wait;
	private String success;
	private String cancel;
	private String absence;
	private int status_total;
	private String ticket_reg;
	private int team;
	private String today;
	private String day;
}

