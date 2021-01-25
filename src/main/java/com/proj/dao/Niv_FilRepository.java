package com.proj.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proj.entities.Niv_Fil;

public interface Niv_FilRepository extends JpaRepository<Niv_Fil, Long>{
	 
	@Query("select nf from Niv_Fil nf"
			+ " where nf.filiere like :x and nf.niveau like :y")
	public Niv_Fil chercher(@Param("x")String filiere,@Param("y")int niveau );

}

