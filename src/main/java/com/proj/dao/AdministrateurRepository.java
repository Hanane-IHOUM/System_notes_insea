package com.proj.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.entities.Administrateur;


public interface AdministrateurRepository extends JpaRepository<Administrateur, Long>{
	
	
}

