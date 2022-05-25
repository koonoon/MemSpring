package com.icia.member.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Alias("member")
public class MemberDTO {

	private String mId;
	private String mPw;
	private String mName;
	private String mBirth;
	private String mGender;
	private String mEmail;
	
	private MultipartFile mProfile;
	private String mProfileName;
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmPw() {
		return mPw;
	}
	public void setmPw(String mPw) {
		this.mPw = mPw;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmBirth() {
		return mBirth;
	}
	public void setmBirth(String mBirth) {
		this.mBirth = mBirth;
	}
	public String getmGender() {
		return mGender;
	}
	public void setmGender(String mGender) {
		this.mGender = mGender;
	}
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	public MultipartFile getmProfile() {
		return mProfile;
	}
	public void setmProfile(MultipartFile mProfile) {
		this.mProfile = mProfile;
	}
	public String getmProfileName() {
		return mProfileName;
	}
	public void setmProfileName(String mProfileName) {
		this.mProfileName = mProfileName;
	}
	@Override
	public String toString() {
		return "MemberDTO [mId=" + mId + ", mPw=" + mPw + ", mName=" + mName + ", mBirth=" + mBirth + ", mGender="
				+ mGender + ", mEmail=" + mEmail + ", mProfile=" + mProfile + ", mProfileName=" + mProfileName + "]";
	}
	
	
}
