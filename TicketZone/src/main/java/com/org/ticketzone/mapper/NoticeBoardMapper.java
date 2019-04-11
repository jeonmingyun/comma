package com.org.ticketzone.mapper;

import java.util.ArrayList;
import java.util.List;

import com.org.ticketzone.domain.Criteria;
import com.org.ticketzone.domain.NoticeBoardVO;

public interface NoticeBoardMapper {
	public ArrayList<NoticeBoardVO> noticeBoardList(Criteria cri);

	public ArrayList<NoticeBoardVO> noticeBoardUpdInfo(String notice_no);
	
	public void noticeBoardUpd(NoticeBoardVO notice);

	public void noticeBoardDel(NoticeBoardVO notice);

	public void insertSelectKey(NoticeBoardVO notice);
	
//	public ArrayList<NoticeBoardVO> noticeSearch(NoticeBoardVO notice);
	
	public int SearchCount(Criteria cri);
	// ∆‰¿Ã¬°
//	public List<NoticeBoardVO> getListWithPaging(Criteria cri);

	public ArrayList<NoticeBoardVO> getListWithPaging(Criteria cri);
	
	public int total(Criteria cri);
}
