package com.org.ticketzone.domain;

import lombok.Data;

@Data
public class StoreMenuVO {
	private String menu_code;
	private String menu_name;
	private String menu_price;
	private String menu_cate;
	private String store_note;
	private String license_number;
	private int count;
	private String reg_date;

}
