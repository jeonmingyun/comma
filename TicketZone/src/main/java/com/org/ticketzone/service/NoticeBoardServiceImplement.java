package com.org.ticketzone.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.Criteria;
import com.org.ticketzone.domain.NoticeBoardVO;
import com.org.ticketzone.mapper.NoticeBoardMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NoticeBoardServiceImplement implements NoticeBoardService {

	private NoticeBoardMapper mapper;

	@Override
	public ArrayList<NoticeBoardVO> noticeBoardList(Criteria cri) {
		return mapper.getListWithPaging(cri);
	}

	@Override
	public ArrayList<NoticeBoardVO> noticeBoardUpdInfo(String notice_no) {
		return mapper.noticeBoardUpdInfo(notice_no);
	}

	@Override
	public void noticeBoardUpd(NoticeBoardVO notice) {
		mapper.noticeBoardUpd(notice);	
	}

	@Override
	public void noticeBoardDel(NoticeBoardVO notice) {
		mapper.noticeBoardDel(notice);
	}

	@Override
	public void noticeInsert(NoticeBoardVO notice) {
		mapper.noticeInsert(notice);		
	}
	
	

	@Override
	public ArrayList<NoticeBoardVO> getListWithPaging(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int total(Criteria cri) {
		
		return mapper.total(cri);
	}

//	@Override
//	public ArrayList<NoticeBoardVO> noticeSearch(NoticeBoardVO notice) {
//		
//		return mapper.noticeSearch(notice);
//	}

	@Override
	public int SearchCount(Criteria cri) {
		
		return mapper.SearchCount(cri);
	}


}
