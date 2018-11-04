package com.blogger.blog.dao;

import org.springframework.data.repository.CrudRepository;

import com.blogger.blog.model.Entry;

public interface EntryDAO extends CrudRepository<Entry, Long>{

}
