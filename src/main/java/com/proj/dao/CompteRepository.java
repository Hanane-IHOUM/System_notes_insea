package com.proj.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long>{
	
	

}

