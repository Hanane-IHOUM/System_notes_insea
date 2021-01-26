package com.proj.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.proj.entities.Exam_Ratt;

public interface Exam_RattRepository extends JpaRepository<Exam_Ratt, Long>{
	
	@Query("select er from Exam_Ratt er"
			+ " where er.etudiant.id like :x and er.element.id like :y")
	public Exam_Ratt chercher(@Param("x")Long idetudiant,@Param("y")Long idElement );

	@Modifying
	@Query("UPDATE Exam_Ratt e SET e.note = :x WHERE e.id = :z")
	@Transactional
	public void edit(@Param("x")float note, @Param("z") Long id);
	
	
	@Query("select er"
			+ " from Exam_Ratt er"
			+ " inner join Element elt on elt.id = er.element.id"
			+ " inner join Module m on m.id = elt.module.id"
			+ " where er.etudiant.id like :x and m.niv_fil.id like :y")
	public List<Exam_Ratt> chercherNoteRatt(@Param("x")Long idetudiant,@Param("y")Long idNivFil );
}

