package com.mx.ssh.bean;

import java.util.Date;

/**
 * MxActivitiesMySpaceMaterial entity. @author MyEclipse Persistence Tools
 */

public class MxActivitiesMySpaceMaterial implements java.io.Serializable {

	// Fields

	private Integer materialId;
	private Integer submitUserId;
	private Integer myspaceId;
	private Integer materialType;
	private String loadUrl;
	private String describe;
	private Date createDate;
	private String others;
	private String previewImgUrl;

	// Constructors

	/** default constructor */
	public MxActivitiesMySpaceMaterial() {
	}

	/** minimal constructor */
	public MxActivitiesMySpaceMaterial(Integer submitUserId, Integer myspaceId,
			Integer materialType, String loadUrl, String describe,
			Date createDate, String previewImgUrl) {
		this.submitUserId = submitUserId;
		this.myspaceId = myspaceId;
		this.materialType = materialType;
		this.loadUrl = loadUrl;
		this.describe = describe;
		this.createDate = createDate;
		this.previewImgUrl = previewImgUrl;
	}

	/** full constructor */
	public MxActivitiesMySpaceMaterial(Integer submitUserId, Integer myspaceId,
			Integer materialType, String loadUrl, String describe,
			Date createDate, String others, String previewImgUrl) {
		this.submitUserId = submitUserId;
		this.myspaceId = myspaceId;
		this.materialType = materialType;
		this.loadUrl = loadUrl;
		this.describe = describe;
		this.createDate = createDate;
		this.others = others;
		this.previewImgUrl = previewImgUrl;
	}

	// Property accessors

	public Integer getMaterialId() {
		return this.materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	public Integer getSubmitUserId() {
		return this.submitUserId;
	}

	public void setSubmitUserId(Integer submitUserId) {
		this.submitUserId = submitUserId;
	}

	public Integer getMyspaceId() {
		return this.myspaceId;
	}

	public void setMyspaceId(Integer myspaceId) {
		this.myspaceId = myspaceId;
	}

	public Integer getMaterialType() {
		return this.materialType;
	}

	public void setMaterialType(Integer materialType) {
		this.materialType = materialType;
	}

	public String getLoadUrl() {
		return this.loadUrl;
	}

	public void setLoadUrl(String loadUrl) {
		this.loadUrl = loadUrl;
	}

	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
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

	public String getPreviewImgUrl() {
		return this.previewImgUrl;
	}

	public void setPreviewImgUrl(String previewImgUrl) {
		this.previewImgUrl = previewImgUrl;
	}

}