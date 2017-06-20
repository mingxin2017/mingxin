package com.bean;

import java.sql.Timestamp;
import java.util.Date;

/**
 * MxUsersReporterSignUp entity. @author MyEclipse Persistence Tools
 */

public class MxUsersReporterSignUp implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private Integer reporterTeamId;
	private Date submitDate;
	private Date auditDate;
	private Integer state;
	private String others;

	// Constructors

	/** default constructor */
	public MxUsersReporterSignUp() {
		this.setUserId(-1);
		this.setReporterTeamId(-1);
		this.setSubmitDate(new Timestamp(System.currentTimeMillis()));
		this.setAuditDate(new Timestamp(0));
		this.setState(0);
		this.setOthers("no set");
	}

	/** minimal constructor */
	public MxUsersReporterSignUp(Integer userId, Integer reporterTeamId,
			Date submitDate, Date auditDate, Integer state) {
		this.userId = userId;
		this.reporterTeamId = reporterTeamId;
		this.submitDate = submitDate;
		this.auditDate = auditDate;
		this.state = state;
	}

	/** full constructor */
	public MxUsersReporterSignUp(Integer userId, Integer reporterTeamId,
			Date submitDate, Date auditDate, Integer state, String others) {
		this.userId = userId;
		this.reporterTeamId = reporterTeamId;
		this.submitDate = submitDate;
		this.auditDate = auditDate;
		this.state = state;
		this.others = others;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getReporterTeamId() {
		return this.reporterTeamId;
	}

	public void setReporterTeamId(Integer reporterTeamId) {
		this.reporterTeamId = reporterTeamId;
	}

	public Date getSubmitDate() {
		return this.submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public Date getAuditDate() {
		return this.auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
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

}