package com.icia.member.service;

import java.io.IOException;

import org.springframework.web.servlet.ModelAndView;

import com.icia.member.dto.MemberDTO;

public interface MemberService {

	ModelAndView mJoin(MemberDTO member) throws IllegalStateException, IOException;

	ModelAndView mList();

}
