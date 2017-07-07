package com.bean;

import java.util.Date;

/**
 * MxActivitiesMySpaceUsers entity. @author MyEclipse Persistence Tools
 */

public class MxActivitiesMySpaceUsers implements java.io.Serializable {

	// Fields

	private Integer myspaceId;
	private Integer userId;
	private Integer relateUserId;
	private Date createDate;
	private Date updateDate;
	private Integer state;
	private String others;
	private Integer remainUploadNum;

	// Constructors

	/** default constructor */
	public MxActivitiesMySpaceUsers() {
	}

	/** minimal constructor */
	public MxActivitiesMySpaceUsers(Integer userId, Integer relateUserId,
			Date createDate, Date updateDate, Integer state,
			Integer remainUploadNum) {
		this.userId = userId;
		this.relateUserId = relateUserId;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.state = state;
		this.remainUploadNum = remainUploadNum;
	}

	/** full constructor */
	public MxActivitiesMySpaceUsers(Integer userId, Integer relateUserId,
			Date createDate, Date updateDate, Integer state, String others,
			Integer remainUploadNum) {
		this.userId = userId;
		this.relateUserId = relateUserId;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.state = state;
		this.others = others;
		this.remainUploadNum = remainUploadNum;
	}

	// Property accessors

	public Integer getMyspaceId() {
		return this.myspaceId;
	}

	public void setMyspaceId(Integer myspaceId) {
		this.myspaceId = myspaceId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

}