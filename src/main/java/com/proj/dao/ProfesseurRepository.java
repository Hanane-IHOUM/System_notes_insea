package com.proj.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.entities.Professeur;

public interface ProfesseurRepository extends JpaRepository<Professeur, Long>{
	
	

}

