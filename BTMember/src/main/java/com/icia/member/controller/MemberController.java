package com.icia.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.member.dto.MemberDTO;
import com.icia.member.service.MemberService;

@Controller
public class MemberController {

	private ModelAndView mav = new ModelAndView();
	
	@Autowired
	private MemberService msvc;
	
	@Autowired
	private HttpSession session;
	
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
	
	// mLoginForm : 로그인 페이지 이동
	@RequestMapping(value="/mLoginForm", method=RequestMethod.GET)
	public String mLoginForm() {
		
		return "M_Login";
	}
	
	// mLogin : 로그인
	@RequestMapping(value="/mLogin", method=RequestMethod.POST)
	public ModelAndView mLogin(@ModelAttribute MemberDTO member) {
		mav = msvc.mLogin(member);
		return mav;
	}
	
	// mLogout
	@RequestMapping(value="/mLogout", method=RequestMethod.GET)
	public String mLogout() {
		session.invalidate();
		return "index";
	}
	
	// mView : 상세보기
	@RequestMapping(value="/mView", method=RequestMethod.GET)
	public ModelAndView mView(@RequestParam("mId") String mId) {
		mav = msvc.mView(mId);
		return mav;
	}
	
	// mModiForm
	@RequestMapping(value="/mModiForm", method=RequestMethod.GET)
	public ModelAndView mModiForm(@RequestParam("mId") String mId) {
		mav = msvc.mModiForm(mId);
		return mav;
	}
	
	// mModify
	@RequestMapping(value="/mModify", method=RequestMethod.POST)
	public ModelAndView mModify(@ModelAttribute MemberDTO member) throws IllegalStateException, IOException {
		mav = msvc.mModify(member);
		return mav;
	}
	
	// mDelete
	@RequestMapping(value="/mDelete", method=RequestMethod.GET)
	public ModelAndView mDelete(@RequestParam("mId") String mId) {
		mav = msvc.mDelete(mId);
		return mav;
	}
	
}
