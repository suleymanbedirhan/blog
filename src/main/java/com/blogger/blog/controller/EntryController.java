package com.blogger.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blogger.blog.model.Entry;
import com.blogger.blog.service.EntryService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Controller
@RequestMapping("entry/")
public class EntryController {
	
	@Autowired
	EntryService entryService;
	
	@PostMapping("add")
	public ResponseEntity<Void> addEntry(Entry entry) {
		entryService.addEntry(entry);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="{entryId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public ResponseEntity<Entry> findEntryById(@PathVariable("entryId") Long id) {
		Entry e = entryService.findEntryById(id);
		return new ResponseEntity<Entry>(e, HttpStatus.OK);
	}
}
