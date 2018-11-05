package com.blogger.blog.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogger.blog.dao.EntryDAO;
import com.blogger.blog.exception.EntryNotFoundException;
import com.blogger.blog.model.Entry;
import com.blogger.blog.service.EntryService;
import com.blogger.blog.view.EntryVO;

@Service
@Transactional
public class EntryServiceImpl implements EntryService{

	@Autowired
	EntryDAO entryDAO;
	
	@Override
	public Entry addEntry(EntryVO entryVo) {
		Entry entry = new Entry();
		entry.setEntryDate(new Date());
		entry.setEntryDetail(entryVo.getEntryDetail());
		entry.setEntryTitle(entryVo.getEntryTitle());
		return entryDAO.save(entry);
	}

	@Override
	public Entry findEntryById(Long id) {
		return entryDAO.findOne(id);
	}

	@Override
	public void deleteEntry(Long id) {
		Entry entry = entryDAO.findOne(id);
		entryDAO.delete(entry);
	}

	@Override
	public List<Entry> findAll() {
		return (List<Entry>) entryDAO.findAll();
	}

	@Override
	public void updateEntry(EntryVO entryVo) {
		Entry entry = entryDAO.findOne(entryVo.getEntryId());
		if(entry == null) {
			throw new EntryNotFoundException("Entry not found");
		}
		entry.setEntryTitle(entryVo.getEntryTitle());
		entry.setEntryDetail(entryVo.getEntryDetail());
		entry.setEntryDate(entryVo.getEntryDate());
		
		entryDAO.save(entry);
	}

}
