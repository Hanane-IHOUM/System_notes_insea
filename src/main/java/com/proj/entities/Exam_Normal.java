package com.proj.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Exam_Normal {

	@Id @GeneratedValue
	private long id;
	
	private float note;
	
	@ManyToOne
	private Etudiant etudiant;
	
	@ManyToOne
	private Element element;

	public Exam_Normal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Exam_Normal(float note, Etudiant etudiant, Element element) {
		super();
		this.note = note;
		this.etudiant = etudiant;
		this.element = element;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getNote() {
		return note;
	}

	public void setNote(float note) {
		this.note = note;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}
	
	
	
}
