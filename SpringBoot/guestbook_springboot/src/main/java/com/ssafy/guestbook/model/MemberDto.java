package com.ssafy.guestbook.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="회원정보",description = "회원의 아이디, 비번, 이름, 이메일, 가입일의 정보를 가진 클래스")
public class MemberDto {

	@ApiModelProperty(value = "회원의 이름")
	private String userName;
	@ApiModelProperty(value = "회원 아이디")
	private String userId;
	@ApiModelProperty(value = "비밀번호")
	private String userPwd;
	@ApiModelProperty(value = "이메일")
	private String email;
	@ApiModelProperty(value = "가입일")
	private String joinDate;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return "MemberDto [userName=" + userName + ", userId=" + userId + ", userPwd=" + userPwd + ", email=" + email
				+ ", joinDate=" + joinDate + "]";
	}

}
