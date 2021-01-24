package com.proj.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.entities.Element;

public interface ElementRepository extends JpaRepository<Element, Long>{
	
	

}

