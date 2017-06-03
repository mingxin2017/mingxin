package com.bean;

/**
 * SysUsers entity. @author MyEclipse Persistence Tools
 */

public class SysUsers implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userLoginName;
	private String userPwd;
	private String userName;
	private String userPhoneNum;
	private String userEmail;
	private Integer userType;
	// Constructors

	/** default constructor */
	public SysUsers() {
	}

	/** minimal constructor */
	public SysUsers(Integer userId, String userLoginName) {
		this.userId = userId;
		this.userLoginName = userLoginName;
	}

	/** full constructor */
	public SysUsers(Integer userId, String userLoginName, String userPwd,
			String userName, String userPhoneNum) {
		this.userId = userId;
		this.userLoginName = userLoginName;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userPhoneNum = userPhoneNum;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserLoginName() {
		return this.userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	public String getUserPwd() {
		return this.userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhoneNum() {
		return this.userPhoneNum;
	}

	public void setUserPhoneNum(String userPhoneNum) {
		this.userPhoneNum = userPhoneNum;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

}