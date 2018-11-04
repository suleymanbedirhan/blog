package com.blogger.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogger.blog.model.Entry;
import com.blogger.blog.service.EntryService;
import com.blogger.blog.view.EntryVO;

@RestController
@RequestMapping("entry")
public class EntryController {
	
	@Autowired
	EntryService entryService;
	
	@GetMapping(value="/list")
	public ResponseEntity<List<Entry>> findAll() {
		List<Entry> entries = entryService.findAll();
		return new ResponseEntity<List<Entry>>(entries, HttpStatus.OK);
	}
	
	@PostMapping(value="/add")
	public ResponseEntity<Entry> saveEntry(@RequestBody EntryVO vo) {
		Entry entry = entryService.addEntry(vo);
		
		return new ResponseEntity<Entry>(entry, HttpStatus.OK);
	}
	
	@GetMapping(value="/{entryId}")
	public ResponseEntity<Entry> findEntryById(@PathVariable("entryId") Long id) {
		Entry entry = entryService.findEntryById(id);
		if(entry == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Entry>(entry, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{entryId}")
	public ResponseEntity<Entry> deleteEntryById(@PathVariable("entryId") Long id) {
		entryService.deleteEntry(id);
		return new ResponseEntity<Entry>(HttpStatus.OK);
	}
	
	
	@PostMapping(value="/update")
	public ResponseEntity<Entry> updateEntry(@RequestBody EntryVO entryVo) {
		
		entryService.updateEntry(entryVo);
		
		return new ResponseEntity<Entry>(HttpStatus.OK);
	}
}
