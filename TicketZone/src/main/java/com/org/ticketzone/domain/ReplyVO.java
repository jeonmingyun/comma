package com.org.ticketzone.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ReplyVO {
	private int board_no;
	private String reply_content;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd hh:mm:ss")
	private Date reply_reg;
	private String admin_id;
}
