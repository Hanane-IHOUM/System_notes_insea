package com.proj.web;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.proj.dao.Niv_FilRepository;
import com.proj.entities.Niv_Fil;



@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	@Autowired
	private Niv_FilRepository niv_filRepository;
	
	
	
	@RequestMapping(value="/formNivFil", method=RequestMethod.GET)
	public String formNivFil(Model model) {
		model.addAttribute("nivfil", new Niv_Fil());
		return "formNivFil";
	}
	
	@RequestMapping(value="/saveNivFil", method=RequestMethod.POST)
	public String saveNivFil(Model model, @Valid Niv_Fil niv_fil, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "formNivFil";
		}
		
		niv_filRepository.save(niv_fil);
		
		return "redirect:/admin";
	}

	
}
