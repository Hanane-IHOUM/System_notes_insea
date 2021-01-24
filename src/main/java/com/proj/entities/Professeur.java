package com.proj.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Professeur {

	@Id @GeneratedValue
	private long id;
	
	@OneToOne
	private Compte compte;
	
	
	@OneToMany(targetEntity = Element.class, mappedBy = "professeur")
	private List<Element> elements;


	public Professeur() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Professeur(Compte compte, List<Element> elements) {
		super();
		this.compte = compte;
		this.elements = elements;
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


	public List<Element> getElements() {
		return elements;
	}


	public void setElements(List<Element> elements) {
		this.elements = elements;
	}
	
	
}
