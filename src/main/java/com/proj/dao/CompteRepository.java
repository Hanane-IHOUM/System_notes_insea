package com.proj.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proj.entities.Compte;


public interface CompteRepository extends JpaRepository<Compte, Long>{
	

	@Query("select c from Compte c where c.username like :x")
	public Compte chercher(@Param("x")String username);
	
	@Query("select c from Compte c where c.email like :x")
	public Compte chercherparEmail(@Param("x")String email);

	@Query("select c from Compte c"
			+ " where c.role like :x ")
	public List<Compte> comptesParRole(@Param("x")String role);

}

