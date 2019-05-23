package com.org.ticketzone.app_mem.vo;

import lombok.Data;

@Data
public class NumberTicketVO {
    private String ticket_code;
    private int wait_number;
    private int the_number;
    private String license_number;
    private String member_id;
    private int ticket_status;
}
