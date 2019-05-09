package com.org.ticketzone.domain;

import lombok.Data;

@Data
public class InqAttachVO {
	private String inq_uuid;
	private String inq_uploadpath;
	private String inq_filename;
	private int board_no;
}
