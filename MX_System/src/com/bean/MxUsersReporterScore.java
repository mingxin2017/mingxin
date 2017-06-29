package com.bean;

/**
 * MxUsersReporterScore entity. @author MyEclipse Persistence Tools
 */

public class MxUsersReporterScore implements java.io.Serializable {

	// Fields

	private Integer userId;
	private Integer teamId;
	private double score;
	private Integer newsNum;
	private Integer commentNum;
	private Integer praiseClickNum;
	private double judgesScore;
	private String others;

	// Constructors

	/** default constructor */
	public MxUsersReporterScore() {
		this.setUserId(-1);
		this.setTeamId(-1);
		this.setScore(0);
		this.setNewsNum(0);
		this.setPraiseClickNum(0);
		this.setJudgesScore(0);
		this.setCommentNum(0);
		this.setOthers("no set");
	}

	/** minimal constructor */
	public MxUsersReporterScore(Integer teamId, double score, Integer newsNum,
			Integer commentNum, Integer praiseClickNum, double judgesScore) {
		this.teamId = teamId;
		this.score = score;
		this.newsNum = newsNum;
		this.commentNum = commentNum;
		this.praiseClickNum = praiseClickNum;
		this.judgesScore = judgesScore;
	}

	/** full constructor */
	public MxUsersReporterScore(Integer teamId, double score, Integer newsNum,
			Integer commentNum, Integer praiseClickNum, double judgesScore,
			String others) {
		this.teamId = teamId;
		this.score = score;
		this.newsNum = newsNum;
		this.commentNum = commentNum;
		this.praiseClickNum = praiseClickNum;
		this.judgesScore = judgesScore;
		this.others = others;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTeamId() {
		return this.teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public double getScore() {
		return this.score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Integer getNewsNum() {
		return this.newsNum;
	}

	public void setNewsNum(Integer newsNum) {
		this.newsNum = newsNum;
	}

	public Integer getCommentNum() {
		return this.commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Integer getPraiseClickNum() {
		return this.praiseClickNum;
	}

	public void setPraiseClickNum(Integer praiseClickNum) {
		this.praiseClickNum = praiseClickNum;
	}

	public double getJudgesScore() {
		return this.judgesScore;
	}

	public void setJudgesScore(double judgesScore) {
		this.judgesScore = judgesScore;
	}

	public String getOthers() {
		return this.others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

}