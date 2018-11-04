package com.blogger.blog.exception;

public class EntryNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	 public EntryNotFoundException(String exception) {
	    	super(exception);
	    }
}
