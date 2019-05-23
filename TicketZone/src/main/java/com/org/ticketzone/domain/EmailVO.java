package com.org.ticketzone.domain;

import lombok.Data;

@Data
public class EmailVO {
	private String senderName; //발신자 이름
	private String senderMail; // 발신자 이메일주소
	private String receiveMail; //수신자 이메일주소
	private String subject; //제목
	private String message; //본문

@Override
public String toString(){
	return "EmailVo [senderName=" + senderName + ", senderMail=" + ", subject=" + subject + ", message=" + message + "]";
	}
}