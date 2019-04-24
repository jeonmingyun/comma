package com.org.ticketzone.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.CategorieVO;
import com.org.ticketzone.mapper.CategorieMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategorieServiceImplement implements CategorieService {

	private CategorieMapper mapper;
	
	@Override
	public ArrayList<CategorieVO> categorieFoodList() {
		
		return mapper.categorieFoodList();
	}

	@Override
	public ArrayList<CategorieVO> boardCateList() {
		// TODO Auto-generated method stub
		return mapper.boardCateList();
	}

}
