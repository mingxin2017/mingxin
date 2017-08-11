package com.mx.ssh.bean;

import java.util.Date;

/**
 * MxActivitiesMySpaceUsers entity. @author MyEclipse Persistence Tools
 */

public class MxActivitiesMySpaceUsers implements java.io.Serializable {

	// Fields

	private Integer id;
	private MxUsersData mxUsersData;
	private Integer myspaceId;
	private Integer relateUserId;
	private Date createDate;
	private Date updateDate;
	private Integer state;
	private String others;
	private Integer remainUploadNum;
	private Date imgUpdateDate;
	private Date commentUpdateDate;

	// Constructors

	/** default constructor */
	public MxActivitiesMySpaceUsers() {
	}

	/** minimal constructor */
	public MxActivitiesMySpaceUsers(MxUsersData mxUsersData, Integer myspaceId,
			Integer relateUserId, Date createDate, Date updateDate,
			Integer state, Integer remainUploadNum, Date imgUpdateDate,
			Date commentUpdateDate) {
		this.mxUsersData = mxUsersData;
		this.myspaceId = myspaceId;
		this.relateUserId = relateUserId;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.state = state;
		this.remainUploadNum = remainUploadNum;
		this.imgUpdateDate = imgUpdateDate;
		this.commentUpdateDate = commentUpdateDate;
	}

	/** full constructor */
	public MxActivitiesMySpaceUsers(MxUsersData mxUsersData, Integer myspaceId,
			Integer relateUserId, Date createDate, Date updateDate,
			Integer state, String others, Integer remainUploadNum,
			Date imgUpdateDate, Date commentUpdateDate) {
		this.mxUsersData = mxUsersData;
		this.myspaceId = myspaceId;
		this.relateUserId = relateUserId;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.state = state;
		this.others = others;
		this.remainUploadNum = remainUploadNum;
		this.imgUpdateDate = imgUpdateDate;
		this.commentUpdateDate = commentUpdateDate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getRelateUserId() {
		return this.relateUserId;
	}

	public void setRelateUserId(Integer relateUserId) {
		this.relateUserId = relateUserId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getOthers() {
		return this.others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public Integer getRemainUploadNum() {
		return this.remainUploadNum;
	}

	public void setRemainUploadNum(Integer remainUploadNum) {
		this.remainUploadNum = remainUploadNum;
	}

	public Date getImgUpdateDate() {
		return this.imgUpdateDate;
	}

	public void setImgUpdateDate(Date imgUpdateDate) {
		this.imgUpdateDate = imgUpdateDate;
	}

	public Date getCommentUpdateDate() {
		return this.commentUpdateDate;
	}

	public void setCommentUpdateDate(Date commentUpdateDate) {
		this.commentUpdateDate = commentUpdateDate;
	}

}