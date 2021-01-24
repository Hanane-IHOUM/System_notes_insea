package com.proj.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Niv_Fil {

	@Id @GeneratedValue
	private long id;
	
	@NotNull
	@Size(min=2,max=15)
	private String filiere;
	
	private int niveau;
	
	@OneToMany(targetEntity = Module.class, mappedBy = "niv_fil")
	private List<Module> modules;
	
	@OneToMany(targetEntity = Etudiant.class, mappedBy = "niv_fil")
	private List<Etudiant> etudiants;

	public Niv_Fil() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Niv_Fil(@NotNull @Size(min = 2, max = 15) String filiere, int niveau, List<Module> modules,
			List<Etudiant> etudiants) {
		super();
		this.filiere = filiere;
		this.niveau = niveau;
		this.modules = modules;
		this.etudiants = etudiants;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFiliere() {
		return filiere;
	}

	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}
	
	
}
