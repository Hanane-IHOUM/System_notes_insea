package com.proj.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.proj.entities.Exam_Normal;

public interface Exam_NormalRepository extends JpaRepository<Exam_Normal, Long>{
	
	@Query("select en from Exam_Normal en"
			+ " where en.etudiant.id like :x and en.element.id like :y")
	public Exam_Normal chercher(@Param("x")Long idetudiant,@Param("y")Long idElement );

	
	@Modifying
	@Query("UPDATE Exam_Normal e SET e.note = :x WHERE e.id = :z")
	@Transactional
	public void edit(@Param("x")float note, @Param("z") Long id);
}

