package com.proj.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proj.entities.Element;

public interface ElementRepository extends JpaRepository<Element, Long>{
	
	@Query("select e from Element e where e.professeur.id like :x")
	public List<Element> chercher(@Param("x")Long idProfesseur);

	@Query("select e from Element e"
			+ " inner join Module m on m.id = e.module.id"
			+ " where m.niv_fil.id like :y")
	public List<Element> cherchercalendrier(@Param("y")Long idNivFil );
}

