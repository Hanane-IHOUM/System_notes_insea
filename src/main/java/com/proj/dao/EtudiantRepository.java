package com.proj.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.entities.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long>{
	
	

}

