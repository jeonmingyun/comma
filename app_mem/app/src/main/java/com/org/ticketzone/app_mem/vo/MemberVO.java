package com.org.ticketzone.app_mem.vo;

import lombok.Data;

@Data
public class MemberVO {
	private String member_tel;
	private String member_name;
	private String member_birth;

	public void setMember_tel(String member_tel){
		this.member_tel = member_tel;
	}
}
