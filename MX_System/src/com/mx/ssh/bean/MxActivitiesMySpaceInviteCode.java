package com.mx.ssh.bean;

import java.util.Date;

/**
 * MxActivitiesMySpaceInviteCode entity. @author MyEclipse Persistence Tools
 */

public class MxActivitiesMySpaceInviteCode implements java.io.Serializable {

	// Fields

	private Integer id;
	private MxActivitiesData mxActivitiesData;
	private Integer myspaceId;
	private Integer initiatorUserId;
	private String inviteCode;
	private Integer state;
	private Integer acceptedUserId;
	private Date createDate;
	private Date updateDate;
	private String others;

	// Constructors

	/** default constructor */
	public MxActivitiesMySpaceInviteCode() {
	}

	/** minimal constructor */
	public MxActivitiesMySpaceInviteCode(MxActivitiesData mxActivitiesData,
			Integer myspaceId, Integer initiatorUserId, String inviteCode,
			Integer state, Integer acceptedUserId, Date createDate) {
		this.mxActivitiesData = mxActivitiesData;
		this.myspaceId = myspaceId;
		this.initiatorUserId = initiatorUserId;
		this.inviteCode = inviteCode;
		this.state = state;
		this.acceptedUserId = acceptedUserId;
		this.createDate = createDate;
	}

	/** full constructor */
	public MxActivitiesMySpaceInviteCode(MxActivitiesData mxActivitiesData,
			Integer myspaceId, Integer initiatorUserId, String inviteCode,
			Integer state, Integer acceptedUserId, Date createDate,
			Date updateDate, String others) {
		this.mxActivitiesData = mxActivitiesData;
		this.myspaceId = myspaceId;
		this.initiatorUserId = initiatorUserId;
		this.inviteCode = inviteCode;
		this.state = state;
		this.acceptedUserId = acceptedUserId;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.others = others;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MxActivitiesData getMxActivitiesData() {
		return this.mxActivitiesData;
	}

	public void setMxActivitiesData(MxActivitiesData mxActivitiesData) {
		this.mxActivitiesData = mxActivitiesData;
	}

	public Integer getMyspaceId() {
		return this.myspaceId;
	}

	public void setMyspaceId(Integer myspaceId) {
		this.myspaceId = myspaceId;
	}

	public Integer getInitiatorUserId() {
		return this.initiatorUserId;
	}

	public void setInitiatorUserId(Integer initiatorUserId) {
		this.initiatorUserId = initiatorUserId;
	}

	public String getInviteCode() {
		return this.inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getAcceptedUserId() {
		return this.acceptedUserId;
	}

	public void setAcceptedUserId(Integer acceptedUserId) {
		this.acceptedUserId = acceptedUserId;
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