package com.org.ticketzone.mapper;

import java.util.ArrayList;

import com.org.ticketzone.domain.InqAttachVO;

public interface InqAttachMapper {
	// 최초 파일 인서트
	public void Inq_fileinsert(InqAttachVO inq);

	// 수정 파일 인서트
	public void Inq_fileModifyinsert(InqAttachVO inq);

	// 파일 삭제
	public void Inq_delete(int board_no);

	// 파일 조회
	public ArrayList<InqAttachVO> Inq_findByBoard_no(int board_no);

}
