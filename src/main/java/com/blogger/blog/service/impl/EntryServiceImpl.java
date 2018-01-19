package com.blogger.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogger.blog.dao.EntryDAO;
import com.blogger.blog.model.Entry;
import com.blogger.blog.service.EntryService;

@Service
public class EntryServiceImpl implements EntryService{

	@Autowired
	EntryDAO entryDAO;
	
	
	@Override
	public void addEntry(Entry entry) {
		entryDAO.addEntry(entry);
	}

	@Override
	public Entry findEntryById(Long id) {
		return entryDAO.findEntryById(id);
	}

	@Override
	public void deleteEntry(Entry entry) {
		entryDAO.deleteEntry(entry);
	}

	@Override
	public List<Entry> findAll() {
		return entryDAO.findAll();
	}

	@Override
	public void updateEntry(Entry entry) {
		entryDAO.updateEntry(entry);
	}

}
