package com.proj.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Element {

	@Id @GeneratedValue
	private long id;
	
	@NotNull
	@Size(min=3,max=100)
	private String elt_nom;
	
	private int pourcentage;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date_normal;
	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date_ratt;
	
	@OneToMany(targetEntity = Exam_Normal.class, mappedBy = "element")
	private List<Exam_Normal> exams_normal;
	
	@OneToMany(targetEntity = Exam_Ratt.class, mappedBy = "element")
	private List<Exam_Ratt> exams_ratt;
	
	@ManyToOne
	private Module module ;
	
	@ManyToOne
	private Professeur professeur;

	public Element() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Element(@NotNull @Size(min = 3, max = 100) String elt_nom, int pourcentage, Date date_normal, Date date_ratt,
			List<Exam_Normal> exams_normal, List<Exam_Ratt> exams_ratt, Module module, Professeur professeur) {
		super();
		this.elt_nom = elt_nom;
		this.pourcentage = pourcentage;
		this.date_normal = date_normal;
		this.date_ratt = date_ratt;
		this.exams_normal = exams_normal;
		this.exams_ratt = exams_ratt;
		this.module = module;
		this.professeur = professeur;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public String getElt_nom() {
		return elt_nom;
	}



	public void setElt_nom(String elt_nom) {
		this.elt_nom = elt_nom;
	}



	public int getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(int pourcentage) {
		this.pourcentage = pourcentage;
	}

	public Date getDate_normal() {
		return date_normal;
	}

	public void setDate_normal(Date date_normal) {
		this.date_normal = date_normal;
	}

	public Date getDate_ratt() {
		return date_ratt;
	}

	public void setDate_ratt(Date date_ratt) {
		this.date_ratt = date_ratt;
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

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Professeur getProfesseur() {
		return professeur;
	}

	public void setProfesseur(Professeur professeur) {
		this.professeur = professeur;
	}
	
	
	
	
	
	
	
	
}
