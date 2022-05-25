package com.icia.member.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private PasswordEncoder pwEnc;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public ModelAndView mJoin(MemberDTO member) throws IllegalStateException, IOException {
		
		UUID uuid = UUID.randomUUID();
		
		// (2)파일처리를 위한 업로드 파일 설정
		MultipartFile mProfile = member.getmProfile();
		
		// (3)업로드 한 파일 이름 설정
		String mProfileName = uuid.toString().substring(0,8) + "_" + mProfile.getOriginalFilename();
		
		// (4)파일업로드 경로 설정
		//String savePath = "C:/Users/user/git/MemSpring/BTMember/src/main/resources/profile/" + fileName;
		
		// 파일 저장 위치 설정
		 Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/profile");
		 String savePath = path + "/"+ mProfileName;
		
		// (5)업로드 한 파일이 있을 경우 실행
		if(!mProfile.isEmpty()) {
			mProfile.transferTo(new File(savePath));
			member.setmProfileName(mProfileName);
		} else {
			member.setmProfileName("default.png");
		}
		
		// 비밀번호 암호화
		member.setmPw(pwEnc.encode(member.getmPw()));
		
		int result = dao.mJoin(member);
		
		if(result>0) {
			System.out.println("등록 성공");
			
			// 가입 축하 메일보내기!
			String str = "<h2>안녕하세요. 인천일보 아카데미 입니다.</h2>"
					+"<p>회원가입을 진심으로 축하드립니다!</p>"
					+ "<p>로그인 후 이용가능 합니다! 로그인 페이지로 이동하시겠습니까?</p>"
					+ "<a href='localhost:9091/mLoginForm'>로그인 하러 가기!</a>";
			
			MimeMessage mail = mailSender.createMimeMessage();
			
			try {
				mail.setSubject("인천일보 아카데미 회원가입");
				mail.setText(str,"UTF-8", "html");
				mail.addRecipient(Message.RecipientType.TO, new InternetAddress(member.getmEmail()));
				
				mailSender.send(mail);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
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

	@Override
	public ModelAndView mLogin(MemberDTO member) {
		
		MemberDTO login = dao.mLogin(member);
		
		if(login != null) {
			session.setAttribute("login", login);
			mav.setViewName("index");
		} else {
			mav.setViewName("M_Login");
		}
		
		return mav;
	}

	@Override
	public ModelAndView mView(String mId) {
		MemberDTO member = dao.mView(mId);
		
		mav.setViewName("M_View");
		mav.addObject("view", member);
		return mav;
	}

	@Override
	public ModelAndView mModiForm(String mId) {
		
		MemberDTO member = dao.mView(mId);
		
		mav.setViewName("M_Modi");
		mav.addObject("modi", member);
		
		return mav;
	}

	@Override
	public ModelAndView mModify(MemberDTO member) throws IllegalStateException, IOException {
		UUID uuid = UUID.randomUUID();
		
		// (2)파일처리를 위한 업로드 파일 설정
		MultipartFile mProfile = member.getmProfile();
		
		// (3)업로드 한 파일 이름 설정
		String mProfileName = uuid.toString().substring(0,8) + "_" + mProfile.getOriginalFilename();
		
		// (4)파일업로드 경로 설정
		//String savePath = "C:/Users/user/git/MemSpring/BTMember/src/main/resources/profile/" + fileName;
		
		// 파일 저장 위치 설정
		 Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/profile");
		 String savePath = path + "/"+ mProfileName;
		
		// (5)업로드 한 파일이 있을 경우 실행
		if(!mProfile.isEmpty()) {
			mProfile.transferTo(new File(savePath));
			member.setmProfileName(mProfileName);
		} else {
			member.setmProfileName("default.png");
		}
		
		int result = dao.mModify(member);
		
		if(result>0) {
			System.out.println("수정 성공");
		} else {
			System.out.println("수정 실패");
		}
		mav.setViewName("redirect:/mView?mId="+member.getmId());
		return mav;
	}

	@Override
	public ModelAndView mDelete(String mId) {
		int result = dao.mDelete(mId);
		
		if(result>0) {
			mav.setViewName("redirect:/mList");
		} else {
			mav.setViewName("redirect:/mList");
		}
		
		return mav;
	}

}
