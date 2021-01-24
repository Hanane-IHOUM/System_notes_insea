package com.proj.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.entities.Module;

public interface ModuleRepository extends JpaRepository<Module, Long>{
	
	

}

