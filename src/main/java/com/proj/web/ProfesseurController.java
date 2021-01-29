package com.proj.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.proj.dao.CompteRepository;
import com.proj.dao.EtudiantRepository;
import com.proj.dao.Exam_NormalRepository;
import com.proj.dao.Exam_RattRepository;
import com.proj.dao.Niv_FilRepository;
import com.proj.dao.ProfesseurRepository;
import com.proj.entities.Compte;
import com.proj.entities.Element;
import com.proj.entities.Etudiant;
import com.proj.entities.Exam_Normal;
import com.proj.entities.Exam_Ratt;
import com.proj.entities.Module;
import com.proj.entities.Niv_Fil;
import com.proj.entities.Professeur;
import com.proj.dao.ModuleRepository;
import com.proj.dao.ElementRepository;



@Controller
@RequestMapping(value="/professeur")
public class ProfesseurController {
	
	@Autowired
	private Niv_FilRepository niv_filRepository;
	
	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private ProfesseurRepository professeurRepository;
	
	@Autowired
	private EtudiantRepository etudiantRepository;
	
	@Autowired
	private ModuleRepository moduleRepository;
	
	@Autowired
	private ElementRepository elementRepository;
	
	@Autowired
	private Exam_NormalRepository examNormalRepository;
	
	@Autowired
	private Exam_RattRepository examRattRepository;
	
	
	@RequestMapping(value="/mesElements")
	public String index(Model model, HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
        Compte compte = compteRepository.chercherparEmail(principal.getName());
        
        Professeur professeur = professeurRepository.chercher(compte.getId());
        
		List <Element> elements = elementRepository.chercher(professeur.getId());
		model.addAttribute("elements", elements);
		model.addAttribute("prof", compte);
		return "listeElements";
	}
	
	@RequestMapping(value="/lesNotesNorm")
	public String noteNorm(Model model ,@RequestParam() Long id) {
		
		
		Optional<Element> e = elementRepository.findById(id);
		
		
		if(e.isPresent()) {
			  Element element = e.get();
	
			  Long IdNF = element.getModule().getNiv_fil().getId();
			  
			  List <Etudiant> etudiants = etudiantRepository.chercher(IdNF);
			 
			  List <Exam_Normal> notesNormal= new ArrayList <Exam_Normal> ();
			
			  for( int i=0; i< etudiants.size();i++) {
				  notesNormal.add(examNormalRepository.chercher(etudiants.get(i).getId(), element.getId())); 
			  }
			  
			  
			  model.addAttribute("notesNormal", notesNormal);
			  model.addAttribute("elt", element.getElt_nom());
			  
			  return "notesEtudiantsNormal";
		}
		
		return "redirect : /professeur";
	}
	
	@RequestMapping(value="/lesNotesRatt")
	public String noteRatt(Model model ,@RequestParam() Long id) {
		
		
		Optional<Element> e = elementRepository.findById(id);
		
		
		if(e.isPresent()) {
			  Element element = e.get();
	
			  Long IdNF = element.getModule().getNiv_fil().getId();
			 
			  List <Etudiant> etudiants = etudiantRepository.chercher(IdNF);
			 
			  List <Exam_Ratt> notesRatt= new ArrayList <Exam_Ratt> ();
			
			  for( int i=0; i< etudiants.size();i++) {
				  notesRatt.add(examRattRepository.chercher(etudiants.get(i).getId(), element.getId())); 
			  }
			  
			  
			  model.addAttribute("notesRatt", notesRatt);
			  model.addAttribute("elt", element.getElt_nom());
			  
			  return "notesEtudiantsRatt";
		}
		
		return "redirect : /professeur";
	}
	
	@RequestMapping(value="/editNoteNormal", method=RequestMethod.POST)
	public String editNoteNormal(Model model, 
							@RequestParam() Long id,
							@RequestParam(name="note", defaultValue="") float note) {
		
	
		Optional<Exam_Normal> en =examNormalRepository.findById(id);
		
		
		if(en.isPresent()) {
			Exam_Normal normal = en.get();
			
			examNormalRepository.edit(note, normal.getId());
			Exam_Ratt er = examRattRepository.chercher(normal.getEtudiant().getId(), normal.getElement().getId());
			examRattRepository.edit(note, er.getId());
			
			return "redirect:/professeur/lesNotesNorm?id="+normal.getElement().getId();
		}
	
		return "redirect:/professeur";
	}
	
	
	@RequestMapping(value="/editNoteRatt", method=RequestMethod.POST)
	public String editNotRatt(Model model, 
							@RequestParam() Long id,
							@RequestParam(name="note", defaultValue="") float note) {
		
		
	
		Optional<Exam_Ratt> er =examRattRepository.findById(id);
		
		
		if(er.isPresent()) {
			Exam_Ratt ratt = er.get();
			
			examRattRepository.edit(note, ratt.getId());
			
			return "redirect:/professeur/lesNotesRatt?id="+ratt.getElement().getId();
		}
	
		return "redirect:/professeur";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}