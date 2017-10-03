package com.mx.ssh.bean;

import java.util.Date;

/**
 * MxNewsPraise entity. @author MyEclipse Persistence Tools
 */

public class MxNewsPraise implements java.io.Serializable {

	// Fields
	private Integer id;
	private Integer newsId;
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
	public MxNewsPraise() {
	}

	/** full constructor */
	public MxNewsPraise(Integer operator, Date createDate) {
		this.operator = operator;
		this.createDate = createDate;
	}

	// Property accessors

	public Integer getNewsId() {
		return this.newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
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