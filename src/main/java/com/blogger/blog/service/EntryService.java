package com.blogger.blog.service;

import java.util.List;

import com.blogger.blog.model.Entry;
import com.blogger.blog.view.EntryVO;

public interface EntryService {

	public Entry addEntry(EntryVO entryVo);
	
	public Entry findEntryById(Long id);
	
	public void deleteEntry(Long id);
	
	public List<Entry> findAll();

	public void updateEntry(EntryVO entryVo);
}
