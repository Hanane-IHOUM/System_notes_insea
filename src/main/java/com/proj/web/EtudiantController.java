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
@RequestMapping(value="/etudiant")
public class EtudiantController {
	
	@Autowired
	private Niv_FilRepository niv_filRepository;
	
	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private EtudiantRepository etudiantRepository;
	
	
	@Autowired
	private Exam_RattRepository examRattRepository;
	
	@Autowired
	private ElementRepository elementRepository;
	
	@RequestMapping(value="/mesNotes")
	public String mesNotes(Model model, HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
        Compte compte = compteRepository.chercherparEmail(principal.getName());
        
        Etudiant etudiant = etudiantRepository.findByCompteId(compte.getId());
        
        //List <Exam_Normal> notesNorm = examNormalRepository.chercherNoteNorm(etudiant.getId(), etudiant.getNiv_fil().getId());
		
        List <Exam_Ratt> notesFin = examRattRepository.chercherNoteRatt(etudiant.getId(), etudiant.getNiv_fil().getId());
        
        model.addAttribute("compte", compte);
        model.addAttribute("etudiant", etudiant);
        model.addAttribute("notesfinales", notesFin);
        
        return "MesNotes";
	}
	
	
	@RequestMapping(value="/Calendrier")
	public String calendrier(Model model, HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
        Compte compte = compteRepository.chercherparEmail(principal.getName());
        
        Etudiant etudiant = etudiantRepository.findByCompteId(compte.getId());
        
        //List <Exam_Normal> notesNorm = examNormalRepository.chercherNoteNorm(etudiant.getId(), etudiant.getNiv_fil().getId());
		
        List <Element> calendrier = elementRepository.cherchercalendrier(etudiant.getNiv_fil().getId());
        
        model.addAttribute("compte", compte);
        model.addAttribute("etudiant", etudiant);
        model.addAttribute("calendrier", calendrier);
        
        return "Calendrier";
	}
	
	
	
}