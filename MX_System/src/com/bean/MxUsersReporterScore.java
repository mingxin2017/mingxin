package com.bean;

/**
 * MxUsersReporterScore entity. @author MyEclipse Persistence Tools
 */

public class MxUsersReporterScore implements java.io.Serializable {

	// Fields

	private MxUsersReporterScoreId id;

	// Constructors

	/** default constructor */
	public MxUsersReporterScore() {
	}

	/** full constructor */
	public MxUsersReporterScore(MxUsersReporterScoreId id) {
		this.id = id;
	}

	// Property accessors

	public MxUsersReporterScoreId getId() {
		return this.id;
	}

	public void setId(MxUsersReporterScoreId id) {
		this.id = id;
	}

}