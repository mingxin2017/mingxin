package com.bean;

import java.util.Date;

/**
 * MxUsersType entity. @author MyEclipse Persistence Tools
 */

public class MxUsersType implements java.io.Serializable {

	// Fields

	private Integer typeId;
	private String typeName;
	private Integer typePower;
	private String typeDescribe;
	private Date createDate;
	private Date updateDate;
	private String others;

	// Constructors

	/** default constructor */
	public MxUsersType() {
	}

	/** minimal constructor */
	public MxUsersType(String typeName, Integer typePower, String typeDescribe,
			Date createDate, Date updateDate) {
		this.typeName = typeName;
		this.typePower = typePower;
		this.typeDescribe = typeDescribe;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	/** full constructor */
	public MxUsersType(String typeName, Integer typePower, String typeDescribe,
			Date createDate, Date updateDate, String others) {
		this.typeName = typeName;
		this.typePower = typePower;
		this.typeDescribe = typeDescribe;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.others = others;
	}

	// Property accessors

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getTypePower() {
		return this.typePower;
	}

	public void setTypePower(Integer typePower) {
		this.typePower = typePower;
	}

	public String getTypeDescribe() {
		return this.typeDescribe;
	}

	public void setTypeDescribe(String typeDescribe) {
		this.typeDescribe = typeDescribe;
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