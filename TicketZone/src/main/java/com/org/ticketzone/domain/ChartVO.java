package com.org.ticketzone.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ChartVO {
	private Timestamp ticket_reg;
	private int total;
}
