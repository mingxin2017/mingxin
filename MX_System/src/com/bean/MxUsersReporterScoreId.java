package com.bean;

/**
 * MxUsersReporterScoreId entity. @author MyEclipse Persistence Tools
 */

public class MxUsersReporterScoreId implements java.io.Serializable {

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
	public MxUsersReporterScoreId() {
	}

	/** minimal constructor */
	public MxUsersReporterScoreId(Integer userId, Integer teamId, double score,
			Integer newsNum, Integer commentNum, Integer praiseClickNum,
			double judgesScore) {
		this.userId = userId;
		this.teamId = teamId;
		this.score = score;
		this.newsNum = newsNum;
		this.commentNum = commentNum;
		this.praiseClickNum = praiseClickNum;
		this.judgesScore = judgesScore;
	}

	/** full constructor */
	public MxUsersReporterScoreId(Integer userId, Integer teamId, double score,
			Integer newsNum, Integer commentNum, Integer praiseClickNum,
			double judgesScore, String others) {
		this.userId = userId;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MxUsersReporterScoreId))
			return false;
		MxUsersReporterScoreId castOther = (MxUsersReporterScoreId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null && castOther.getUserId() != null && this
				.getUserId().equals(castOther.getUserId())))
				&& ((this.getTeamId() == castOther.getTeamId()) || (this
						.getTeamId() != null && castOther.getTeamId() != null && this
						.getTeamId().equals(castOther.getTeamId())))
				&& (this.getScore() == castOther.getScore())
				&& ((this.getNewsNum() == castOther.getNewsNum()) || (this
						.getNewsNum() != null && castOther.getNewsNum() != null && this
						.getNewsNum().equals(castOther.getNewsNum())))
				&& ((this.getCommentNum() == castOther.getCommentNum()) || (this
						.getCommentNum() != null
						&& castOther.getCommentNum() != null && this
						.getCommentNum().equals(castOther.getCommentNum())))
				&& ((this.getPraiseClickNum() == castOther.getPraiseClickNum()) || (this
						.getPraiseClickNum() != null
						&& castOther.getPraiseClickNum() != null && this
						.getPraiseClickNum().equals(
								castOther.getPraiseClickNum())))
				&& (this.getJudgesScore() == castOther.getJudgesScore())
				&& ((this.getOthers() == castOther.getOthers()) || (this
						.getOthers() != null && castOther.getOthers() != null && this
						.getOthers().equals(castOther.getOthers())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getTeamId() == null ? 0 : this.getTeamId().hashCode());
		result = 37 * result + (int) this.getScore();
		result = 37 * result
				+ (getNewsNum() == null ? 0 : this.getNewsNum().hashCode());
		result = 37
				* result
				+ (getCommentNum() == null ? 0 : this.getCommentNum()
						.hashCode());
		result = 37
				* result
				+ (getPraiseClickNum() == null ? 0 : this.getPraiseClickNum()
						.hashCode());
		result = 37 * result + (int) this.getJudgesScore();
		result = 37 * result
				+ (getOthers() == null ? 0 : this.getOthers().hashCode());
		return result;
	}

}