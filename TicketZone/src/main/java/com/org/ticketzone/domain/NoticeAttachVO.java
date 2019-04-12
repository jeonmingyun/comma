package com.org.ticketzone.domain;

import lombok.Data;

@Data
public class NoticeAttachVO {
	private String uuid;
	private String uploadPath;
	private String fileName;
	private String fileType;
	private int notice_no;
	
}
