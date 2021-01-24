package com.proj.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Etudiant {

	@Id @GeneratedValue
	private long id;
	
	@OneToOne
	private Compte compte;
	
	
	@OneToMany(targetEntity = Exam_Normal.class, mappedBy = "etudiant")
	private List<Exam_Normal> exams_normal;
	
	@OneToMany(targetEntity = Exam_Ratt.class, mappedBy = "etudiant")
	private List<Exam_Ratt> exams_ratt;
	
	@ManyToOne
	private Niv_Fil niv_fil ;

	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Etudiant(Compte compte, List<Exam_Normal> exams_normal, List<Exam_Ratt> exams_ratt, Niv_Fil niv_fil) {
		super();
		this.compte = compte;
		this.exams_normal = exams_normal;
		this.exams_ratt = exams_ratt;
		this.niv_fil = niv_fil;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public List<Exam_Normal> getExams_normal() {
		return exams_normal;
	}

	public void setExams_normal(List<Exam_Normal> exams_normal) {
		this.exams_normal = exams_normal;
	}

	public List<Exam_Ratt> getExams_ratt() {
		return exams_ratt;
	}

	public void setExams_ratt(List<Exam_Ratt> exams_ratt) {
		this.exams_ratt = exams_ratt;
	}

	public Niv_Fil getNiv_fil() {
		return niv_fil;
	}

	public void setNiv_fil(Niv_Fil niv_fil) {
		this.niv_fil = niv_fil;
	}
	
	
}
