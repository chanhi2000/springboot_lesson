package com.sist.hr.user.controller;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sist.hr.user.domain.User;
import com.sist.hr.user.service.UserSvc;

@Controller
public class UserController {

	private final Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserSvc userSvc;
	private final String RETURN_URL = "user/user";
	
	@RequestMapping(value="user/do_get.do",method=RequestMethod.GET)
	public String  get(User user,Model model) throws ClassNotFoundException, SQLException{
		log.debug("1++++++++++++++++++++++++++++");
		log.debug("1+user+"+user);
		log.debug("1++++++++++++++++++++++++++++");
		User outVO = userSvc.get(user.getU_id());
		log.debug("1.1++++++++++++++++++++++++++++");
		log.debug("1.1+outVO+"+outVO);
		log.debug("1.1.++++++++++++++++++++++++++++");
		
		model.addAttribute("vo", outVO);
		return RETURN_URL;
	}
	
	@RequestMapping(value="user/do_sel.do",method=RequestMethod.POST)
	public String del(User user,Model model) throws ClassNotFoundException, SQLException{
		log.debug("1++++++++++++++++++++++++++++");
		log.debug("1+user+"+user);
		log.debug("1++++++++++++++++++++++++++++");
		
		int flag = userSvc.del(user.getU_id());
		
		log.debug("1.1++++++++++++++++++++++++++++");
		log.debug("1.1+flag+"+flag);
		log.debug("1.1.++++++++++++++++++++++++++++");		
		return RETURN_URL;
	}
	
}
