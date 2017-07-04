package com.bean;

import java.util.Date;

/**
 * MxActivitiesMySpaceUsers entity. @author MyEclipse Persistence Tools
 */

public class MxActivitiesMySpaceUsers implements java.io.Serializable {

	// Fields

	private Integer myspaceId;
	private Integer activitiesId;
	private Integer userId;
	private Date createDate;
	private Date updateDate;
	private String others;

	// Constructors

	/** default constructor */
	public MxActivitiesMySpaceUsers() {
	}

	/** minimal constructor */
	public MxActivitiesMySpaceUsers(Integer activitiesId, Integer userId,
			Date createDate, Date updateDate) {
		this.activitiesId = activitiesId;
		this.userId = userId;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	/** full constructor */
	public MxActivitiesMySpaceUsers(Integer activitiesId, Integer userId,
			Date createDate, Date updateDate, String others) {
		this.activitiesId = activitiesId;
		this.userId = userId;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.others = others;
	}

	// Property accessors

	public Integer getMyspaceId() {
		return this.myspaceId;
	}

	public void setMyspaceId(Integer myspaceId) {
		this.myspaceId = myspaceId;
	}

	public Integer getActivitiesId() {
		return this.activitiesId;
	}

	public void setActivitiesId(Integer activitiesId) {
		this.activitiesId = activitiesId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getOthers() {
		return this.others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

}