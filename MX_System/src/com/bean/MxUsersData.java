package com.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * MxUsersData entity. @author MyEclipse Persistence Tools
 */

public class MxUsersData implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userName;
	private String password;
	private Integer userTypeId;
	private String weixinOpenId;
	private String weixinNikeName;
	private String weixinHeadUrl;
	private Date subscribeTime;
	private String weixinRemark;
	private Integer userSex;
	private String weixinGroupid;
	private String weixinTagidList;
	private Integer userAge;
	private Integer userSchoolId;
	private String userEmail;
	private String userPhoneNum;
	private Integer reporterTeamId;
	private String userTags;
	private Integer userState;
	private Integer userRegionId;
	private String userAddr;
	private Date lastLoginTime;
	private String others;
	private String userRealName;
	private String userIdcardNum;
	private String userQqNum;
	private Set mxActivitiesMySpaceUserses = new HashSet(0);
	private Set mxActivitiesMySpaceComments = new HashSet(0);

	// Constructors

	/** default constructor */
	public MxUsersData() {
	}

	/** minimal constructor */
	public MxUsersData(String userName, String password, Integer userTypeId,
			String weixinOpenId, String weixinNikeName, String weixinHeadUrl,
			Date subscribeTime, String weixinRemark, Integer userSex,
			String weixinGroupid, String weixinTagidList, Integer userAge,
			Integer userSchoolId, String userEmail, String userPhoneNum,
			Integer reporterTeamId, String userTags, Integer userState,
			Integer userRegionId, String userAddr, Date lastLoginTime,
			String userRealName, String userIdcardNum, String userQqNum) {
		this.userName = userName;
		this.password = password;
		this.userTypeId = userTypeId;
		this.weixinOpenId = weixinOpenId;
		this.weixinNikeName = weixinNikeName;
		this.weixinHeadUrl = weixinHeadUrl;
		this.subscribeTime = subscribeTime;
		this.weixinRemark = weixinRemark;
		this.userSex = userSex;
		this.weixinGroupid = weixinGroupid;
		this.weixinTagidList = weixinTagidList;
		this.userAge = userAge;
		this.userSchoolId = userSchoolId;
		this.userEmail = userEmail;
		this.userPhoneNum = userPhoneNum;
		this.reporterTeamId = reporterTeamId;
		this.userTags = userTags;
		this.userState = userState;
		this.userRegionId = userRegionId;
		this.userAddr = userAddr;
		this.lastLoginTime = lastLoginTime;
		this.userRealName = userRealName;
		this.userIdcardNum = userIdcardNum;
		this.userQqNum = userQqNum;
	}

	/** full constructor */
	public MxUsersData(String userName, String password, Integer userTypeId,
			String weixinOpenId, String weixinNikeName, String weixinHeadUrl,
			Date subscribeTime, String weixinRemark, Integer userSex,
			String weixinGroupid, String weixinTagidList, Integer userAge,
			Integer userSchoolId, String userEmail, String userPhoneNum,
			Integer reporterTeamId, String userTags, Integer userState,
			Integer userRegionId, String userAddr, Date lastLoginTime,
			String others, String userRealName, String userIdcardNum,
			String userQqNum, Set mxActivitiesMySpaceUserses,
			Set mxActivitiesMySpaceComments) {
		this.userName = userName;
		this.password = password;
		this.userTypeId = userTypeId;
		this.weixinOpenId = weixinOpenId;
		this.weixinNikeName = weixinNikeName;
		this.weixinHeadUrl = weixinHeadUrl;
		this.subscribeTime = subscribeTime;
		this.weixinRemark = weixinRemark;
		this.userSex = userSex;
		this.weixinGroupid = weixinGroupid;
		this.weixinTagidList = weixinTagidList;
		this.userAge = userAge;
		this.userSchoolId = userSchoolId;
		this.userEmail = userEmail;
		this.userPhoneNum = userPhoneNum;
		this.reporterTeamId = reporterTeamId;
		this.userTags = userTags;
		this.userState = userState;
		this.userRegionId = userRegionId;
		this.userAddr = userAddr;
		this.lastLoginTime = lastLoginTime;
		this.others = others;
		this.userRealName = userRealName;
		this.userIdcardNum = userIdcardNum;
		this.userQqNum = userQqNum;
		this.mxActivitiesMySpaceUserses = mxActivitiesMySpaceUserses;
		this.mxActivitiesMySpaceComments = mxActivitiesMySpaceComments;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserTypeId() {
		return this.userTypeId;
	}

	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getWeixinOpenId() {
		return this.weixinOpenId;
	}

	public void setWeixinOpenId(String weixinOpenId) {
		this.weixinOpenId = weixinOpenId;
	}

	public String getWeixinNikeName() {
		return this.weixinNikeName;
	}

	public void setWeixinNikeName(String weixinNikeName) {
		this.weixinNikeName = weixinNikeName;
	}

	public String getWeixinHeadUrl() {
		return this.weixinHeadUrl;
	}

	public void setWeixinHeadUrl(String weixinHeadUrl) {
		this.weixinHeadUrl = weixinHeadUrl;
	}

	public Date getSubscribeTime() {
		return this.subscribeTime;
	}

	public void setSubscribeTime(Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public String getWeixinRemark() {
		return this.weixinRemark;
	}

	public void setWeixinRemark(String weixinRemark) {
		this.weixinRemark = weixinRemark;
	}

	public Integer getUserSex() {
		return this.userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public String getWeixinGroupid() {
		return this.weixinGroupid;
	}

	public void setWeixinGroupid(String weixinGroupid) {
		this.weixinGroupid = weixinGroupid;
	}

	public String getWeixinTagidList() {
		return this.weixinTagidList;
	}

	public void setWeixinTagidList(String weixinTagidList) {
		this.weixinTagidList = weixinTagidList;
	}

	public Integer getUserAge() {
		return this.userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	public Integer getUserSchoolId() {
		return this.userSchoolId;
	}

	public void setUserSchoolId(Integer userSchoolId) {
		this.userSchoolId = userSchoolId;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhoneNum() {
		return this.userPhoneNum;
	}

	public void setUserPhoneNum(String userPhoneNum) {
		this.userPhoneNum = userPhoneNum;
	}

	public Integer getReporterTeamId() {
		return this.reporterTeamId;
	}

	public void setReporterTeamId(Integer reporterTeamId) {
		this.reporterTeamId = reporterTeamId;
	}

	public String getUserTags() {
		return this.userTags;
	}

	public void setUserTags(String userTags) {
		this.userTags = userTags;
	}

	public Integer getUserState() {
		return this.userState;
	}

	public void setUserState(Integer userState) {
		this.userState = userState;
	}

	public Integer getUserRegionId() {
		return this.userRegionId;
	}

	public void setUserRegionId(Integer userRegionId) {
		this.userRegionId = userRegionId;
	}

	public String getUserAddr() {
		return this.userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getOthers() {
		return this.others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public String getUserRealName() {
		return this.userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getUserIdcardNum() {
		return this.userIdcardNum;
	}

	public void setUserIdcardNum(String userIdcardNum) {
		this.userIdcardNum = userIdcardNum;
	}

	public String getUserQqNum() {
		return this.userQqNum;
	}

	public void setUserQqNum(String userQqNum) {
		this.userQqNum = userQqNum;
	}

	public Set getMxActivitiesMySpaceUserses() {
		return this.mxActivitiesMySpaceUserses;
	}

	public void setMxActivitiesMySpaceUserses(Set mxActivitiesMySpaceUserses) {
		this.mxActivitiesMySpaceUserses = mxActivitiesMySpaceUserses;
	}

	public Set getMxActivitiesMySpaceComments() {
		return this.mxActivitiesMySpaceComments;
	}

	public void setMxActivitiesMySpaceComments(Set mxActivitiesMySpaceComments) {
		this.mxActivitiesMySpaceComments = mxActivitiesMySpaceComments;
	}

}