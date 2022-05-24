package com.icia.member.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.icia.member.dto.MemberDTO;
import com.icia.member.service.MemberService;

@Controller
public class MemberController {

	private ModelAndView mav = new ModelAndView();
	
	@Autowired
	private MemberService msvc;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	// mJoin
	@RequestMapping(value="/mJoin", method=RequestMethod.POST)
	public ModelAndView mJoin(@ModelAttribute MemberDTO member) throws IllegalStateException, IOException {
		mav = msvc.mJoin(member);
		return mav;
	}
	
	// mList
	@RequestMapping(value="/mList", method=RequestMethod.GET)
	public ModelAndView mList() {
		mav = msvc.mList();
		return mav;
	}
}
