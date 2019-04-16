package com.org.ticketzone.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class NoticeBoardVO {
	private int notice_no;
	private String notice_title;
	private String notice_content;
	private Date notice_reg;
	private String cate_code;
	private String keyword;
		
}
