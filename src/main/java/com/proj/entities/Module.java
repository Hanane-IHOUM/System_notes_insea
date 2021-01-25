package com.proj.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Module {

	@Id @GeneratedValue
	private long id;
	
	@NotNull
	@Size(min=3,max=100)
	private String nom;
	
	@OneToMany(targetEntity = Element.class, mappedBy = "module")
	private List<Element> elements;
	
	@ManyToOne
	private Niv_Fil niv_fil ;

	public Module() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Module(@NotNull @Size(min = 3, max = 100) String nom, List<Element> elements, Niv_Fil niv_fil) {
		super();
		this.nom = nom;
		this.elements = elements;
		this.niv_fil = niv_fil;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	public Niv_Fil getNiv_fil() {
		return niv_fil;
	}

	public void setNiv_fil(Niv_Fil niv_fil) {
		this.niv_fil = niv_fil;
	}
	
	
}
