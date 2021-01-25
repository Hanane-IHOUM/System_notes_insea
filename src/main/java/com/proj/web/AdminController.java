package com.proj.web;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.proj.dao.CompteRepository;
import com.proj.dao.Niv_FilRepository;
import com.proj.dao.ProfesseurRepository;
import com.proj.entities.Compte;
import com.proj.entities.Element;
import com.proj.entities.Niv_Fil;
import com.proj.entities.Professeur;



@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	@Autowired
	private Niv_FilRepository niv_filRepository;
	
	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private ProfesseurRepository professeurRepository;
	
	
	@RequestMapping(value="/formNivFil", method=RequestMethod.GET)
	public String formNivFil(Model model) {
		model.addAttribute("nivfil", new Niv_Fil());
		return "formNivFil";
	}
	
	@RequestMapping(value="/saveNivFil", method=RequestMethod.POST)
	public String save(Model model, @Valid Niv_Fil niv_fil, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "formNivFil";
		}
		
		niv_filRepository.save(niv_fil);
		
		return "redirect:/admin";
	}
	
	@RequestMapping(value="/formProfesseur", method=RequestMethod.GET)
	public String formProfesseur(Model model) {
		model.addAttribute("compte", new Compte());
		return "formProfesseur";
	}
	
	@RequestMapping(value="/saveProfesseur", method=RequestMethod.POST)
	public String saveProfesseur(Model model, @Valid Compte compte, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "formProfesseur";
		}
		compte.setRole("PROFESSEUR");
		compte.setActive(true);
		compteRepository.save(compte);
		
		Professeur professeur = new Professeur();
		professeur.setCompte(compte);
		professeurRepository.save(professeur);

		return "redirect:/admin";
	}

}
