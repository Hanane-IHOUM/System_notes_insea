package com.proj.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proj.entities.Compte;
import com.proj.entities.Etudiant;


public interface EtudiantRepository extends JpaRepository<Etudiant, Long>{

	@Query("select e from Etudiant e where e.niv_fil.id like :x")
	public List<Etudiant> chercher(@Param("x")Long idNivFil);

	@Query("select e from Etudiant e where e.compte.id like :x")
	public Etudiant findByCompteId(@Param("x")long compte_id);

	@Query("select e from Etudiant e where e.compte.username like :x")
	public Etudiant findByUsername(@Param("x")String username);
}

