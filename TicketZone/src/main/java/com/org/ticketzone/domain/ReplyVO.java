package com.org.ticketzone.domain;

import lombok.Data;

@Data
public class ReplyVO {
	private String board_no;
	private String reply_content;
	private String reply_reg;
	private String admin_id;
}
