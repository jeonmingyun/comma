package com.org.ticketzone.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StoreCriteria {
	private int pageNum;
	private int amount;
	
	private String keyword;
	private String type;
	
	public StoreCriteria() {
		this(1, 4);
	}
	
	public StoreCriteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() {
		return type == null? new String[] {}: type.split("");
	}
}
