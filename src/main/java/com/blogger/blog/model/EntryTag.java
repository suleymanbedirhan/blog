package com.blogger.blog.model;

import java.util.Date;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class EntryTag {

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "entryId", insertable = false, updatable = false)
	private Entry entry;

	private int entryId;

	@ManyToOne
	@JoinColumn(name = "tagId", insertable = false, updatable = false)
	private Tag tag;

	private int tagId;

}