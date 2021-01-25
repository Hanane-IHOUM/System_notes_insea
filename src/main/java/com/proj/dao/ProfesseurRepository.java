package com.proj.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proj.entities.Professeur;

public interface ProfesseurRepository extends JpaRepository<Professeur, Long>{
	
	@Query("select p from Professeur p where p.compte.id like :x")
	public Professeur chercher(@Param("x")Long idCompte);

}

