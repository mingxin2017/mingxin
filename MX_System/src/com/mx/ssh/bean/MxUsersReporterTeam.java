package com.mx.ssh.bean;

import java.util.Date;

/**
 * MxUsersReporterTeam entity. @author MyEclipse Persistence Tools
 */

public class MxUsersReporterTeam implements java.io.Serializable {

	// Fields

	private Integer teamId;
	private String teamName;
	private Integer teamCaptainId;
	private Integer peopleLimit;
	private double teamScore;
	private Integer teamRegionId;
	private Integer teamSchoolId;
	private String teamSlogan;
	private Integer teamState;
	private Date createDate;
	private Date updateDate;
	private String others;

	// Constructors

	/** default constructor */
	public MxUsersReporterTeam() {
	}

	/** minimal constructor */
	public MxUsersReporterTeam(String teamName, Integer teamCaptainId,
			Integer peopleLimit, double teamScore, Integer teamRegionId,
			Integer teamSchoolId, String teamSlogan, Integer teamState,
			Date createDate, Date updateDate) {
		this.teamName = teamName;
		this.teamCaptainId = teamCaptainId;
		this.peopleLimit = peopleLimit;
		this.teamScore = teamScore;
		this.teamRegionId = teamRegionId;
		this.teamSchoolId = teamSchoolId;
		this.teamSlogan = teamSlogan;
		this.teamState = teamState;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	/** full constructor */
	public MxUsersReporterTeam(String teamName, Integer teamCaptainId,
			Integer peopleLimit, double teamScore, Integer teamRegionId,
			Integer teamSchoolId, String teamSlogan, Integer teamState,
			Date createDate, Date updateDate, String others) {
		this.teamName = teamName;
		this.teamCaptainId = teamCaptainId;
		this.peopleLimit = peopleLimit;
		this.teamScore = teamScore;
		this.teamRegionId = teamRegionId;
		this.teamSchoolId = teamSchoolId;
		this.teamSlogan = teamSlogan;
		this.teamState = teamState;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.others = others;
	}

	// Property accessors

	public Integer getTeamId() {
		return this.teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return this.teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getTeamCaptainId() {
		return this.teamCaptainId;
	}

	public void setTeamCaptainId(Integer teamCaptainId) {
		this.teamCaptainId = teamCaptainId;
	}

	public Integer getPeopleLimit() {
		return this.peopleLimit;
	}

	public void setPeopleLimit(Integer peopleLimit) {
		this.peopleLimit = peopleLimit;
	}

	public double getTeamScore() {
		return this.teamScore;
	}

	public void setTeamScore(double teamScore) {
		this.teamScore = teamScore;
	}

	public Integer getTeamRegionId() {
		return this.teamRegionId;
	}

	public void setTeamRegionId(Integer teamRegionId) {
		this.teamRegionId = teamRegionId;
	}

	public Integer getTeamSchoolId() {
		return this.teamSchoolId;
	}

	public void setTeamSchoolId(Integer teamSchoolId) {
		this.teamSchoolId = teamSchoolId;
	}

	public String getTeamSlogan() {
		return this.teamSlogan;
	}

	public void setTeamSlogan(String teamSlogan) {
		this.teamSlogan = teamSlogan;
	}

	public Integer getTeamState() {
		return this.teamState;
	}

	public void setTeamState(Integer teamState) {
		this.teamState = teamState;
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