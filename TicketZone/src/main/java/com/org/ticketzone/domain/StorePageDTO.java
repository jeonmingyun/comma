package com.org.ticketzone.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StorePageDTO {
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	private int total;
	private String keyword;
	private String type;
	private StoreCriteria cri;
	
	public StorePageDTO(StoreCriteria cri, int total) {
		this.cri = cri;
		this.total = total;
		
		this.endPage = (int)(Math.ceil(cri.getPageNum() / 5.0)) * 5;
		this.startPage = this.endPage - 4;
		
		int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
	public String[] getTypeArr() {
		return type == null? new String[] {}: type.split("");
	}
}
