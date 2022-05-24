package com.icia.member.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("member")
public class MemberDTO {

	private String mId;
	private String mPw;
	private String mName;
	private String mBirth;
	private String mGender;
	private String mEmail;
	
}
