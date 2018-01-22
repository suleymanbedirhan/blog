package com.blogger.blog.view;

import java.util.Date;

/**
 * 
 * @author silemanbed
 *
 */
public class EntryVO {
	
	
	private String entryTitle;
	
	private String entryDetail;
	
	private Date entryDate;
	
	public String getEntryTitle() {
		return entryTitle;
	}

	public void setEntryTitle(String entryTitle) {
		this.entryTitle = entryTitle;
	}

	public String getEntryDetail() {
		return entryDetail;
	}

	public void setEntryDetail(String entryDetail) {
		this.entryDetail = entryDetail;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

}