package com.bean;

/**
 * MxNewsContent entity. @author MyEclipse Persistence Tools
 */

public class MxNewsContent implements java.io.Serializable {

	// Fields

	private Integer newsContentId;
	private Integer newsId;
	private String newsHeadline;
	private String newsLeadText;
	private String newsMainContent;
	private String others;

	// Constructors

	/** default constructor */
	public MxNewsContent() {
		this.setNewsContentId(0);
		this.setNewsId(-1);
		this.setNewsHeadline("no set");
		this.setNewsLeadText("no set");
		this.setNewsMainContent("no set");
		this.setOthers("no set");
	}

	/** minimal constructor */
	public MxNewsContent(Integer newsId, String newsHeadline,
			String newsLeadText, String newsMainContent) {
		this.newsId = newsId;
		this.newsHeadline = newsHeadline;
		this.newsLeadText = newsLeadText;
		this.newsMainContent = newsMainContent;
	}

	/** full constructor */
	public MxNewsContent(Integer newsId, String newsHeadline,
			String newsLeadText, String newsMainContent, String others) {
		this.newsId = newsId;
		this.newsHeadline = newsHeadline;
		this.newsLeadText = newsLeadText;
		this.newsMainContent = newsMainContent;
		this.others = others;
	}

	// Property accessors

	public Integer getNewsContentId() {
		return this.newsContentId;
	}

	public void setNewsContentId(Integer newsContentId) {
		this.newsContentId = newsContentId;
	}

	public Integer getNewsId() {
		return this.newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public String getNewsHeadline() {
		return this.newsHeadline;
	}

	public void setNewsHeadline(String newsHeadline) {
		this.newsHeadline = newsHeadline;
	}

	public String getNewsLeadText() {
		return this.newsLeadText;
	}

	public void setNewsLeadText(String newsLeadText) {
		this.newsLeadText = newsLeadText;
	}

	public String getNewsMainContent() {
		return this.newsMainContent;
	}

	public void setNewsMainContent(String newsMainContent) {
		this.newsMainContent = newsMainContent;
	}

	public String getOthers() {
		return this.others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

}