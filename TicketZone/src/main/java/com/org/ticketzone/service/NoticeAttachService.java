package com.org.ticketzone.service;

import java.util.ArrayList;
import java.util.List;

import com.org.ticketzone.domain.NoticeAttachVO;
import com.org.ticketzone.domain.NoticeBoardVO;

public interface NoticeAttachService {
	
	public void Fileinsert(NoticeAttachVO attach);
	
	public ArrayList<NoticeAttachVO> findByNotice_no(String notice_no);
}
