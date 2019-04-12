package com.org.ticketzone.service;

import java.util.ArrayList;
import java.util.List;

import com.org.ticketzone.domain.Criteria;
import com.org.ticketzone.domain.NoticeBoardVO;


public interface NoticeBoardService {
	public ArrayList<NoticeBoardVO> noticeBoardList(Criteria cri); //게시글 리스트 반환
	public ArrayList<NoticeBoardVO> noticeBoardUpdInfo(String notice_no); // 수정창으로 이동
	public void noticeBoardUpd(NoticeBoardVO notice); // 게시글 수정
	public void noticeBoardDel(NoticeBoardVO notice); // 게시글 삭제
	public void insertSelectKey(NoticeBoardVO notice); // 게시글 작성
	public ArrayList<NoticeBoardVO> getListWithPaging(Criteria cri);
	public int total(Criteria cri);
//	public ArrayList<NoticeBoardVO> noticeSearch(NoticeBoardVO notice);// 게시글 검색
	public int SearchCount(Criteria cri);
	
	
}
