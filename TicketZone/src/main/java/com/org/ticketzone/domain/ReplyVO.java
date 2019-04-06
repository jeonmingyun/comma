package com.org.ticketzone.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {
	private int board_no;
	private String reply_content;
	private Date reply_reg;
	private String admin_id;
}
