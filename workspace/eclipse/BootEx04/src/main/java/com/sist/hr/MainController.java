package com.sist.hr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="main/main.do")
	public String hello(Model model) {
		log.debug("============================");
		log.debug("hello=");
		log.debug("============================");
		model.addAttribute("msg", "Hello Spring Web!");
		//"main/main"--> prefix(/) /main/main.jsp suffix(.jsp)
		return "main/main";
	}
}
