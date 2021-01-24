package com.proj.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Administrateur {

	@Id @GeneratedValue
	private long id;
	
	@OneToOne
	private Compte compte;

	public Administrateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Administrateur(Compte compte) {
		super();
		this.compte = compte;
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
	
	
	
}
