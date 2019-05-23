package com.org.ticketzone.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.InqAttachVO;
import com.org.ticketzone.mapper.InqAttachMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InqAttachServiceImplement implements InqAttachService {

	private InqAttachMapper mapper;

	@Override
	public void Inq_fileinsert(InqAttachVO inq) {

		mapper.Inq_fileinsert(inq);

	}

	@Override
	public void Inq_fileModifyinsert(InqAttachVO inq) {

		mapper.Inq_fileModifyinsert(inq);

	}

	@Override
	public void Inq_delete(int board_no) {

		mapper.Inq_delete(board_no);
	}

	@Override
	public ArrayList<InqAttachVO> Inq_findByBoard_no(int board_no) {

		return mapper.Inq_findByBoard_no(board_no);
	}

}
