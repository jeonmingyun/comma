package com.org.ticketzone.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.org.ticketzone.domain.OwnerVO;

public interface OwnerMapper {
	public ArrayList<OwnerVO> login(String id);
	
	public void insertOwner(OwnerVO owner);
	
	public ArrayList<OwnerVO> findOwner(@Param("owner_name") String owner_name,@Param("owner_tel") String owner_tel);

	// 비밀번호 찾기
	

}