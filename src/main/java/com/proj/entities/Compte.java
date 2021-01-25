package com.proj.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Compte {

	@Id @GeneratedValue
	private long id;
	
	@NotNull
	@Size(min=3,max=15)
	private String username;
	
	
	@NotNull
	@Size(min=3,max=50)
	private String email;
	
	
	@NotNull
	@Size(min=3,max=50)
	private String password;
	
	private String role;
	
	private boolean active;

	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Compte(@NotNull @Size(min = 3, max = 15) String username, @NotNull @Size(min = 3, max = 50) String email,
			@NotNull @Size(min = 3, max = 50) String password, String role,
			boolean active) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.active = active;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
	
}
