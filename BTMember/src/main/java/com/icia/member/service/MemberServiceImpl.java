package com.icia.member.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.member.dao.MemberDAO;
import com.icia.member.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService{

	private ModelAndView mav = new ModelAndView();
	
	@Autowired
	private MemberDAO dao;
	
	@Override
	public ModelAndView mJoin(MemberDTO member) throws IllegalStateException, IOException {
		
		
		int result = dao.mJoin(member);
		
		if(result>0) {
			System.out.println("등록 성공");
		} else {
			System.out.println("등록 실패");
		}
		mav.setViewName("redirect:/mList");
		return mav;
	}

	@Override
	public ModelAndView mList() {
		
		List<MemberDTO> mList = dao.mList();
		
		mav.addObject("member", mList);
		mav.setViewName("index");
		return mav;
	}

}
