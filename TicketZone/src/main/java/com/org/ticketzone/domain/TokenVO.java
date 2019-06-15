package com.org.ticketzone.domain;

import lombok.Data;

@Data
public class TokenVO {
	private String token_id;
	private String member_id;
	private String license_number;
}
