package com.mx.ssh.bean;

import java.util.Date;

/**
 * MxActivitiesData entity. @author MyEclipse Persistence Tools
 */

public class MxActivitiesData implements java.io.Serializable {

	// Fields

	private Integer activitiesId;
	private MxUsersData mxUsersData;
	private String activitiesName;
	private Integer activitiesTypeId;
	private String activitiesDescribe;
	private Integer state;
	private Date createDate;
	private Date updateDate;
	private String others;
	private Integer upperLimit;
	private Integer lowerLimit;
	private String coverImageUrl;

	// Constructors

	/** default constructor */
	public MxActivitiesData() {
	}

	/** minimal constructor */
	public MxActivitiesData(MxUsersData mxUsersData, String activitiesName,
			Integer activitiesTypeId, String activitiesDescribe, Integer state,
			Date createDate, Date updateDate, Integer upperLimit,
			Integer lowerLimit, String coverImageUrl) {
		this.mxUsersData = mxUsersData;
		this.activitiesName = activitiesName;
		this.activitiesTypeId = activitiesTypeId;
		this.activitiesDescribe = activitiesDescribe;
		this.state = state;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.upperLimit = upperLimit;
		this.lowerLimit = lowerLimit;
		this.coverImageUrl = coverImageUrl;
	}

	/** full constructor */
	public MxActivitiesData(MxUsersData mxUsersData, String activitiesName,
			Integer activitiesTypeId, String activitiesDescribe, Integer state,
			Date createDate, Date updateDate, String others,
			Integer upperLimit, Integer lowerLimit, String coverImageUrl) {
		this.mxUsersData = mxUsersData;
		this.activitiesName = activitiesName;
		this.activitiesTypeId = activitiesTypeId;
		this.activitiesDescribe = activitiesDescribe;
		this.state = state;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.others = others;
		this.upperLimit = upperLimit;
		this.lowerLimit = lowerLimit;
		this.coverImageUrl = coverImageUrl;
	}

	// Property accessors

	public Integer getActivitiesId() {
		return this.activitiesId;
	}

	public void setActivitiesId(Integer activitiesId) {
		this.activitiesId = activitiesId;
	}

	public MxUsersData getMxUsersData() {
		return this.mxUsersData;
	}

	public void setMxUsersData(MxUsersData mxUsersData) {
		this.mxUsersData = mxUsersData;
	}

	public String getActivitiesName() {
		return this.activitiesName;
	}

	public void setActivitiesName(String activitiesName) {
		this.activitiesName = activitiesName;
	}

	public Integer getActivitiesTypeId() {
		return this.activitiesTypeId;
	}

	public void setActivitiesTypeId(Integer activitiesTypeId) {
		this.activitiesTypeId = activitiesTypeId;
	}

	public String getActivitiesDescribe() {
		return this.activitiesDescribe;
	}

	public void setActivitiesDescribe(String activitiesDescribe) {
		this.activitiesDescribe = activitiesDescribe;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public Integer getUpperLimit() {
		return this.upperLimit;
	}

	public void setUpperLimit(Integer upperLimit) {
		this.upperLimit = upperLimit;
	}

	public Integer getLowerLimit() {
		return this.lowerLimit;
	}

	public void setLowerLimit(Integer lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public String getCoverImageUrl() {
		return this.coverImageUrl;
	}

	public void setCoverImageUrl(String coverImageUrl) {
		this.coverImageUrl = coverImageUrl;
	}

}