package com.org.ticketzone.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.NoticeAttachVO;
import com.org.ticketzone.domain.NoticeBoardVO;
import com.org.ticketzone.mapper.NoticeAttachMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NoticeAttachImplement implements NoticeAttachService {
	
	private NoticeAttachMapper mapper;

	@Override
	public void Fileinsert(NoticeAttachVO attach) {
		mapper.Fileinsert(attach);
		
	}

	@Override
	public ArrayList<NoticeAttachVO> findByNotice_no(String notice_no) {
		
		return mapper.findByNotice_no(notice_no);
	}
	
	

}
