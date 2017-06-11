package com.bean;

/**
 * MxActivitiesPublicityContent entity. @author MyEclipse Persistence Tools
 */

public class MxActivitiesPublicityContent implements java.io.Serializable {

	// Fields

	private Integer publicityArticleId;
	private Integer publicityDataId;
	private String articleContent;
	private String others;

	// Constructors

	/** default constructor */
	public MxActivitiesPublicityContent() {
	}

	/** minimal constructor */
	public MxActivitiesPublicityContent(Integer publicityArticleId,
			Integer publicityDataId, String articleContent) {
		this.publicityArticleId = publicityArticleId;
		this.publicityDataId = publicityDataId;
		this.articleContent = articleContent;
	}

	/** full constructor */
	public MxActivitiesPublicityContent(Integer publicityArticleId,
			Integer publicityDataId, String articleContent, String others) {
		this.publicityArticleId = publicityArticleId;
		this.publicityDataId = publicityDataId;
		this.articleContent = articleContent;
		this.others = others;
	}

	// Property accessors

	public Integer getPublicityArticleId() {
		return this.publicityArticleId;
	}

	public void setPublicityArticleId(Integer publicityArticleId) {
		this.publicityArticleId = publicityArticleId;
	}

	public Integer getPublicityDataId() {
		return this.publicityDataId;
	}

	public void setPublicityDataId(Integer publicityDataId) {
		this.publicityDataId = publicityDataId;
	}

	public String getArticleContent() {
		return this.articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public String getOthers() {
		return this.others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

}