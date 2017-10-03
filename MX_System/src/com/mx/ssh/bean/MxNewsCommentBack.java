package com.mx.ssh.bean;

import java.util.Date;

/**
 * MxNewsCommentBack entity. @author MyEclipse Persistence Tools
 */

public class MxNewsCommentBack implements java.io.Serializable {

	// Fields

	private Integer commentId;
	private Integer operator;
	private Date createDate;
	private String commentBack;
	private Integer id;
	private String weixinHeadUrl;
	private String weixinNikeName;
	

	// Constructors

	public String getWeixinHeadUrl() {
		return weixinHeadUrl;
	}

	public void setWeixinHeadUrl(String weixinHeadUrl) {
		this.weixinHeadUrl = weixinHeadUrl;
	}

	public String getWeixinNikeName() {
		return weixinNikeName;
	}

	public void setWeixinNikeName(String weixinNikeName) {
		this.weixinNikeName = weixinNikeName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/** default constructor */
	public MxNewsCommentBack() {
	}

	/** full constructor */
	public MxNewsCommentBack(Integer operator, Date createDate,
			String commentBack) {
		this.operator = operator;
		this.createDate = createDate;
		this.commentBack = commentBack;
	}

	// Property accessors

	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getOperator() {
		return this.operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCommentBack() {
		return this.commentBack;
	}

	public void setCommentBack(String commentBack) {
		this.commentBack = commentBack;
	}

}