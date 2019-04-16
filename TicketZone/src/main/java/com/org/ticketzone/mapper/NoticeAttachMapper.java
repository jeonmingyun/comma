package com.org.ticketzone.mapper;

import java.util.ArrayList;

import com.org.ticketzone.domain.NoticeAttachVO;

public interface NoticeAttachMapper {
	
	public void Fileinsert(NoticeAttachVO attach);
	
	public ArrayList<NoticeAttachVO> findByNotice_no(String notice_no);
}
