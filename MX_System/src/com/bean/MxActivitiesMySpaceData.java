package com.bean;

import java.util.Date;

/**
 * MxActivitiesMySpaceData entity. @author MyEclipse Persistence Tools
 */

public class MxActivitiesMySpaceData implements java.io.Serializable {

	// Fields

	private Integer myspaceId;
	private Integer activitiesId;
	private String describe;
	private Date createDate;
	private Date updateDate;
	private String others;
	private Integer upperLimit;
	private Integer lowerLimit;
	private Integer state;
	private String coverImageUrl;
	private String myspaceName;

	// Constructors

	/** default constructor */
	public MxActivitiesMySpaceData() {
	}

	/** minimal constructor */
	public MxActivitiesMySpaceData(Integer activitiesId, String describe,
			Date createDate, Date updateDate, Integer upperLimit,
			Integer lowerLimit, Integer state, String coverImageUrl,
			String myspaceName) {
		this.activitiesId = activitiesId;
		this.describe = describe;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.upperLimit = upperLimit;
		this.lowerLimit = lowerLimit;
		this.state = state;
		this.coverImageUrl = coverImageUrl;
		this.myspaceName = myspaceName;
	}

	/** full constructor */
	public MxActivitiesMySpaceData(Integer activitiesId, String describe,
			Date createDate, Date updateDate, String others,
			Integer upperLimit, Integer lowerLimit, Integer state,
			String coverImageUrl, String myspaceName) {
		this.activitiesId = activitiesId;
		this.describe = describe;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.others = others;
		this.upperLimit = upperLimit;
		this.lowerLimit = lowerLimit;
		this.state = state;
		this.coverImageUrl = coverImageUrl;
		this.myspaceName = myspaceName;
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

	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
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

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCoverImageUrl() {
		return this.coverImageUrl;
	}

	public void setCoverImageUrl(String coverImageUrl) {
		this.coverImageUrl = coverImageUrl;
	}

	public String getMyspaceName() {
		return this.myspaceName;
	}

	public void setMyspaceName(String myspaceName) {
		this.myspaceName = myspaceName;
	}

}