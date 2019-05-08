package com.org.ticketzone.domain;

import lombok.Data;

@Data
public class OwnerVO {
	private String owner_id;
	private String owner_password;
	private String owner_name;
	private String owner_tel;
	private String email;
	
//	public OwnerVO(String owner_id, String owner_password) {
//		this.owner_id = owner_id;
//		this.owner_password = owner_password;
//	}
}
