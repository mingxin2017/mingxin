package com.bean;

import java.util.Date;

/**
 * MxActivitiesMySpaceComment entity. @author MyEclipse Persistence Tools
 */

public class MxActivitiesMySpaceComment implements java.io.Serializable {

	// Fields

	private Integer commentId;
	private MxUsersData mxUsersData;
	private Integer myspaceId;
	private Integer state;
	private String commentTxt;
	private Integer praiseClickNum;
	private Date createDate;
	private String others;

	// Constructors

	/** default constructor */
	public MxActivitiesMySpaceComment() {
	}

	/** minimal constructor */
	public MxActivitiesMySpaceComment(MxUsersData mxUsersData,
			Integer myspaceId, Integer state, String commentTxt,
			Integer praiseClickNum, Date createDate) {
		this.mxUsersData = mxUsersData;
		this.myspaceId = myspaceId;
		this.state = state;
		this.commentTxt = commentTxt;
		this.praiseClickNum = praiseClickNum;
		this.createDate = createDate;
	}

	/** full constructor */
	public MxActivitiesMySpaceComment(MxUsersData mxUsersData,
			Integer myspaceId, Integer state, String commentTxt,
			Integer praiseClickNum, Date createDate, String others) {
		this.mxUsersData = mxUsersData;
		this.myspaceId = myspaceId;
		this.state = state;
		this.commentTxt = commentTxt;
		this.praiseClickNum = praiseClickNum;
		this.createDate = createDate;
		this.others = others;
	}

	// Property accessors

	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public MxUsersData getMxUsersData() {
		return this.mxUsersData;
	}

	public void setMxUsersData(MxUsersData mxUsersData) {
		this.mxUsersData = mxUsersData;
	}

	public Integer getMyspaceId() {
		return this.myspaceId;
	}

	public void setMyspaceId(Integer myspaceId) {
		this.myspaceId = myspaceId;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCommentTxt() {
		return this.commentTxt;
	}

	public void setCommentTxt(String commentTxt) {
		this.commentTxt = commentTxt;
	}

	public Integer getPraiseClickNum() {
		return this.praiseClickNum;
	}

	public void setPraiseClickNum(Integer praiseClickNum) {
		this.praiseClickNum = praiseClickNum;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getOthers() {
		return this.others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

}