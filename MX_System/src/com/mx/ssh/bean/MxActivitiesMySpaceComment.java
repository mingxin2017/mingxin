package com.mx.ssh.bean;

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
	private String praiseUserIds;
	private Integer praiseClickNum;
	private Date createDate;
	private String others;
	private Integer parentCommentId;

	// Constructors

	/** default constructor */
	public MxActivitiesMySpaceComment() {
	}

	/** minimal constructor */
	public MxActivitiesMySpaceComment(MxUsersData mxUsersData,
			Integer myspaceId, Integer state, String commentTxt,
			String praiseUserIds, Integer praiseClickNum, Date createDate,
			Integer parentCommentId) {
		this.mxUsersData = mxUsersData;
		this.myspaceId = myspaceId;
		this.state = state;
		this.commentTxt = commentTxt;
		this.praiseUserIds = praiseUserIds;
		this.praiseClickNum = praiseClickNum;
		this.createDate = createDate;
		this.parentCommentId = parentCommentId;
	}

	/** full constructor */
	public MxActivitiesMySpaceComment(MxUsersData mxUsersData,
			Integer myspaceId, Integer state, String commentTxt,
			String praiseUserIds, Integer praiseClickNum, Date createDate,
			String others, Integer parentCommentId) {
		this.mxUsersData = mxUsersData;
		this.myspaceId = myspaceId;
		this.state = state;
		this.commentTxt = commentTxt;
		this.praiseUserIds = praiseUserIds;
		this.praiseClickNum = praiseClickNum;
		this.createDate = createDate;
		this.others = others;
		this.parentCommentId = parentCommentId;
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

	public String getPraiseUserIds() {
		return this.praiseUserIds;
	}

	public void setPraiseUserIds(String praiseUserIds) {
		this.praiseUserIds = praiseUserIds;
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

	public Integer getParentCommentId() {
		return this.parentCommentId;
	}

	public void setParentCommentId(Integer parentCommentId) {
		this.parentCommentId = parentCommentId;
	}

}