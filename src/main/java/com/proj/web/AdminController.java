package com.proj.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value="/admin")
public class AdminController {
	
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
	private Exam_NormalRepository exam_normalRepository;
	
	@Autowired
	private Exam_RattRepository exam_rattRepository;
	
	
	@RequestMapping(value="")
	public String accessDneied(Model model,HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
        Compte compte = compteRepository.chercherparEmail(principal.getName());
        
        model.addAttribute("admin", compte);
		return "admin";
	}
	
	
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
	
	@RequestMapping(value="/formEtudiant", method=RequestMethod.GET)
	public String formEtudiant(Model model) {
		model.addAttribute("nivfil", new Niv_Fil());
		model.addAttribute("compte", new Compte());
		return "formEtudiant";
	}
	
	@RequestMapping(value="/saveEtudiant", method=RequestMethod.POST)
	public String saveEtudiant(Model model, @Valid Compte compte, @Valid Niv_Fil niv_fil, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "formEtudiant";
		}
		compte.setRole("ETUDIANT");
		compte.setActive(true);
		compteRepository.save(compte);
		
		Etudiant etudiant = new Etudiant();
		etudiant.setCompte(compte);
		etudiant.setNiv_fil(niv_filRepository.chercher(niv_fil.getFiliere(), niv_fil.getNiveau()));
		etudiantRepository.save(etudiant);
		
		List<Module> modules = etudiant.getNiv_fil().getModules();
		for(Module module : modules) {
			List<Element> elements = module.getElements();
			for(Element element : elements) {
				Exam_Normal exam_normal = new Exam_Normal(0,etudiant,element);
				Exam_Ratt exam_ratt = new Exam_Ratt(0,etudiant,element);
				exam_normalRepository.save(exam_normal);
				exam_rattRepository.save(exam_ratt);
			}
		}

		return "redirect:/admin";
	}


	@RequestMapping(value="/formModule", method=RequestMethod.GET)
	public String formModule(Model model) {
		model.addAttribute("module", new Module());
		model.addAttribute("element1", new Element());
		model.addAttribute("nivfil", new Niv_Fil());
		//model.addAttribute("element2", new Element());
		
		return "formModule";
	}
	
	@RequestMapping(value="/saveModule", method=RequestMethod.POST)
	public String saveModule(Model model, 
							@Valid  Module module,  @Valid Niv_Fil niv_fil, @Valid  Element element1, /*@Valid  Element element2, */
							@RequestParam(name="professeur1", defaultValue="") String professeur1,
							@RequestParam(name="professeur2", defaultValue="") String professeur2,
							@RequestParam(name="nomelement", defaultValue="") String nomelement,
							@RequestParam(name="pourcentageelm") int pourcentage,
							@RequestParam(name = "date1", defaultValue = "2020-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1, 
							@RequestParam(name = "date2", defaultValue = "2020-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2, 
							BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "formModule";
		}
		
		Niv_Fil nf = niv_filRepository.chercher(niv_fil.getFiliere(), niv_fil.getNiveau());
		module.setNiv_fil(nf);
		
		moduleRepository.save(module);
		
		Compte c1 = compteRepository.chercher(professeur1);
		System.out.println("########################");
		System.out.println(c1.getId());
		System.out.println("########################");
		
		Professeur p1 = professeurRepository.chercher(c1.getId());
		
		element1.setProfesseur(p1);
		element1.setModule(module);
		elementRepository.save(element1);
		
		Compte c2 = compteRepository.chercher(professeur2);
		
		Professeur p2 = professeurRepository.chercher(c2.getId());
		
		Element element2 =  new Element();
		
		element2.setElt_nom(nomelement);
		element2.setPourcentage(pourcentage);
		element2.setDate_normal(date1);
		element2.setDate_ratt(date2);
		element2.setProfesseur(p2);
		element2.setModule(module);
		
		elementRepository.save(element2);
		
		
		return "redirect:/admin";
	}
	@RequestMapping(value="/afficherProfesseurs", method=RequestMethod.GET)
	public String afficherProfesseurs(Model model) {
	
		List<Compte> comptes = compteRepository.comptesParRole("PROFESSEUR");
		List<Professeur> professeurs = new ArrayList<>();
		for(Compte compte : comptes) {
			System.out.println(compte.getId());
			professeurs.add(professeurRepository.chercher(compte.getId()));
		}
		
		model.addAttribute("professeurs", professeurs);

		return "afficherProfesseurs";
	}
	
	@RequestMapping(value="/afficherEtudiants", method=RequestMethod.GET)
	public String afficherEtudiants(Model model) {
	
		List<Compte> comptes = compteRepository.comptesParRole("ETUDIANT");
		
		List<Etudiant> etudiants = new ArrayList<>();
		System.out.println("########################################");
		for(Compte compte : comptes) {
			System.out.println(compte.getId());
			etudiants.add(etudiantRepository.findByCompteId(compte.getId()));
		}
		
		model.addAttribute("etudiants", etudiants);

		return "afficherEtudiants";
	}
	
	@RequestMapping(value="/afficherEtudiant", method=RequestMethod.GET)
	public String afficherEtudiant(Model model, @RequestParam(name="username", defaultValue="") String username, HttpServletRequest request) {
	
		Etudiant etudiant = etudiantRepository.findByUsername(username);
		List<Etudiant> etudiants = new ArrayList<>();
		etudiants.add(etudiant);
		
		model.addAttribute("etudiants", etudiants);

		return "afficherEtudiants";
	}
	
	@RequestMapping(value="/afficherProfesseur", method=RequestMethod.GET)
	public String afficherProfesseur(Model model, @RequestParam(name="username", defaultValue="") String username, HttpServletRequest request) {
	
		Professeur professeur = professeurRepository.findByUsername(username);
		List<Professeur> professeurs = new ArrayList<>();
		professeurs.add(professeur);
		
		model.addAttribute("professeurs", professeurs);

		return "afficherProfesseurs";
	}
	
	@RequestMapping(value="/supprimerProfesseur", method=RequestMethod.GET)
	public String supprimerProfesseur(Model model, @RequestParam(name="id", defaultValue="") Long id, HttpServletRequest request) {
		
		Professeur professeur = professeurRepository.getOne(id);
		long idCompte = professeur.getCompte().getId();
		professeurRepository.deleteById(id);
		compteRepository.deleteById(idCompte);
		
		List<Compte> comptes = compteRepository.comptesParRole("PROFESSEUR");
		List<Professeur> professeurs = new ArrayList<>();
		for(Compte compte : comptes) {
			professeurs.add(professeurRepository.chercher(compte.getId()));
		}
		
		model.addAttribute("professeurs", professeurs);

		return "afficherProfesseurs";
	}
	
	@RequestMapping(value="/supprimerEtudiant", method=RequestMethod.GET)
	public String supprimerEtudiant(Model model, @RequestParam(name="id", defaultValue="") Long id, HttpServletRequest request) {
	
		Etudiant etudiant = etudiantRepository.getOne(id);
		long idCompte = etudiant.getCompte().getId();
		etudiantRepository.deleteById(id);
		compteRepository.deleteById(idCompte);
		
		List<Compte> comptes = compteRepository.comptesParRole("ETUDIANT");
		List<Etudiant> etudiants = new ArrayList<>();
		for(Compte compte : comptes) {
			etudiants.add(etudiantRepository.findByCompteId(compte.getId()));
		}
		
		model.addAttribute("etudiants", etudiants);

		return "afficherEtudiants";
	}
	
}




