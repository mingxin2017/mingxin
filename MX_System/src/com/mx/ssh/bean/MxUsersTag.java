package com.mx.ssh.bean;

import java.util.Date;

/**
 * MxUsersTag entity. @author MyEclipse Persistence Tools
 */

public class MxUsersTag implements java.io.Serializable {

	// Fields

	private Integer tagId;
	private String tagName;
	private Integer userNum;
	private Date createDate;
	private Date updateDate;
	private String others;

	// Constructors

	/** default constructor */
	public MxUsersTag() {
	}

	/** minimal constructor */
	public MxUsersTag(String tagName, Integer userNum, Date createDate,
			Date updateDate) {
		this.tagName = tagName;
		this.userNum = userNum;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	/** full constructor */
	public MxUsersTag(String tagName, Integer userNum, Date createDate,
			Date updateDate, String others) {
		this.tagName = tagName;
		this.userNum = userNum;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.others = others;
	}

	// Property accessors

	public Integer getTagId() {
		return this.tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return this.tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Integer getUserNum() {
		return this.userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
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