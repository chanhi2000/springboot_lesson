package com.sist1.hr13;

import org.apache.log4j.Logger;

public class MemberDao implements CommonDao {
	Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public void do_save() {
		log.debug("//////////////////////////////");
		log.debug("///////////do_save////////////");
		log.debug("//////////////////////////////");
	}
	
	@Override
	public void do_update() {
		log.debug("//////////////////////////////");
		log.debug("///////////do_update//////////");
		log.debug("//////////////////////////////");
	}
	
	@Override
	public void do_delete() {
		log.debug("//////////////////////////////");
		log.debug("///////////do_save////////////");
		log.debug("//////////////////////////////");
		
	}
	
	@Override
	public void do_retrieve() {
		log.debug("//////////////////////////////");
		log.debug("//////////do_retrieve/////////");
		log.debug("//////////////////////////////");
	}
}
