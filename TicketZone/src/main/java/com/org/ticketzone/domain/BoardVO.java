package com.org.ticketzone.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	private int board_no;
	private String board_title;
	private String board_content;
	private String member_tel;
	private Date board_reg;
	private int board_status;
	private String board_password;
	private String cate_code;
	private int count;
}
