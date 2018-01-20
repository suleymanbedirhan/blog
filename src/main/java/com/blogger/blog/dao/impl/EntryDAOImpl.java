package com.blogger.blog.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blogger.blog.dao.EntryDAO;
import com.blogger.blog.model.Entry;

@Transactional
@Repository
public class EntryDAOImpl implements EntryDAO {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void addEntry(Entry entry) {
		entityManager.persist(entry);
	}

	@Override
	public Entry findEntryById(Long id) {
		String sql = "from Entry where id = " + id;
		Entry entry = null; 
		try {
			entry = (Entry) entityManager.createQuery(sql).getSingleResult();
		}catch(NoResultException e) {}
		
		return entry;
	}

	@Override
	public void deleteEntry(Entry entry) {
		entityManager.remove(entry);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Entry> findAll() {
		String sql = "select * from entry";
		return (List<Entry>) entityManager.createQuery(sql).getResultList();
	}

	/*test comment1*/
	@Override
	public void updateEntry(Entry entry) {
		Entry e = findEntryById(entry.getEntryId());
		e.setEntryDate(entry.getEntryDate());
		e.setEntryDetail(entry.getEntryDetail());
		e.setEntryTitle(entry.getEntryTitle());
		entityManager.flush();
	}

}
