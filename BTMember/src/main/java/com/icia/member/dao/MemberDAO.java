package com.icia.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.icia.member.dto.MemberDTO;

@Mapper
public interface MemberDAO {

	int mJoin(MemberDTO member);

	List<MemberDTO> mList();

}
