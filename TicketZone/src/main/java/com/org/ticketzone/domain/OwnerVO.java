package com.org.ticketzone.domain;

import lombok.Data;

@Data
public class OwnerVO {
	private String owner_id;
	private String owner_password;
	private String owner_name;
	private String owner_tel;
	private String email;
}
