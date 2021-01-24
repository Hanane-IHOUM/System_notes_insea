package com.proj.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {
	
	
	@RequestMapping(value="/")
	public String home(HttpServletRequest request) {
		
		boolean isEtud = request.isUserInRole("ETUDIANT");
		boolean isProf = request.isUserInRole("PROFESSEUR");
		
		if(isEtud) {
			return "redirect:/etudiant";
		}
		else {
			if(isProf){
				return "redirect:/professeur";
			}
		}
		return "redirect:/admin";
	}
	
	
	@RequestMapping(value="/403")
	public String accessDneied() {
		return "403";
	}
	
	@RequestMapping(value="login")
	public String login() {
		return "login";
	}

}
