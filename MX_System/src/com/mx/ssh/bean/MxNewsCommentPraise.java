package com.mx.ssh.bean;

import java.util.Date;

/**
 * MxNewsCommentPraise entity. @author MyEclipse Persistence Tools
 */

public class MxNewsCommentPraise implements java.io.Serializable {

	// Fields
    private Integer id;
	private Integer commentId;
	private Integer operator;
	private Date createDate;

	// Constructors

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/** default constructor */
	public MxNewsCommentPraise() {
	}

	/** full constructor */
	public MxNewsCommentPraise(Integer operator, Date createDate) {
		this.operator = operator;
		this.createDate = createDate;
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

}