package com.mx.ssh.bean;

import java.util.Date;

/**
 * MxNewsComment entity. @author MyEclipse Persistence Tools
 */

public class MxNewsComment implements java.io.Serializable {

	// Fields

	private Integer commentId;
	private Integer newsId;
	private String commentTxt;
	private Integer praiseClickNum;
	private Date createDate;
	private String others;
	private Integer state;
	private Integer operator;
	private MxUsersData mxUsersData;
	private String weixinNikeName;
    private String weixinHeadUrl;
    private String createDateStr;
    private String commentBackNum;
    private String praiseNum;
    private String praiseState;
    private String backNum;
    private String newsHeadline;
	// Constructors

    


	public MxUsersData getMxUsersData() {
		return mxUsersData;
	}


	public String getNewsHeadline() {
		return newsHeadline;
	}


	public void setNewsHeadline(String newsHeadline) {
		this.newsHeadline = newsHeadline;
	}


	public String getBackNum() {
		return backNum;
	}


	public void setBackNum(String backNum) {
		this.backNum = backNum;
	}


	public String getPraiseNum() {
		return praiseNum;
	}


	public void setPraiseNum(String praiseNum) {
		this.praiseNum = praiseNum;
	}


	public String getPraiseState() {
		return praiseState;
	}


	public void setPraiseState(String praiseState) {
		this.praiseState = praiseState;
	}


	public String getCommentBackNum() {
		return commentBackNum;
	}


	public void setCommentBackNum(String commentBackNum) {
		this.commentBackNum = commentBackNum;
	}


	public String getCreateDateStr() {
		return createDateStr;
	}


	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}


	public String getWeixinNikeName() {
		return weixinNikeName;
	}


	public void setWeixinNikeName(String weixinNikeName) {
		this.weixinNikeName = weixinNikeName;
	}


	public String getWeixinHeadUrl() {
		return weixinHeadUrl;
	}


	public void setWeixinHeadUrl(String weixinHeadUrl) {
		this.weixinHeadUrl = weixinHeadUrl;
	}


	public void setMxUsersData(MxUsersData mxUsersData) {
		this.mxUsersData = mxUsersData;
	}


	/** default constructor */
	public MxNewsComment() {
	}

	/** minimal constructor */
	public MxNewsComment(Integer commentId, Integer newsId, String commentTxt,
			Integer praiseClickNum, Date createDate, Integer state,MxUsersData mxUsersData) {
		this.commentId = commentId;
		this.newsId = newsId;
		this.commentTxt = commentTxt;
		this.praiseClickNum = praiseClickNum;
		this.createDate = createDate;
		this.state = state;
		this.mxUsersData = mxUsersData;
		
	}

	/** full constructor */
	public MxNewsComment(Integer commentId, Integer newsId, String commentTxt,
			Integer praiseClickNum, Date createDate, String others,
			Integer state,MxUsersData mxUsersData) {
		this.commentId = commentId;
		this.newsId = newsId;
		this.commentTxt = commentTxt;
		this.praiseClickNum = praiseClickNum;
		this.createDate = createDate;
		this.others = others;
		this.state = state;
		this.mxUsersData = mxUsersData;
	}

	// Property accessors

	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}


	public Integer getNewsId() {
		return this.newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public String getCommentTxt() {
		return this.commentTxt;
	}

	public void setCommentTxt(String commentTxt) {
		this.commentTxt = commentTxt;
	}

	public Integer getPraiseClickNum() {
		return this.praiseClickNum;
	}

	public void setPraiseClickNum(Integer praiseClickNum) {
		this.praiseClickNum = praiseClickNum;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getOthers() {
		return this.others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}