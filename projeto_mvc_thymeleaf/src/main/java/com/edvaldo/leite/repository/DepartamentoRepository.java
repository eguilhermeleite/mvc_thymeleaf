package com.edvaldo.leite.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edvaldo.leite.domain.Departamento;

@Repository
public interface DepartamentoRepository  extends JpaRepository<Departamento, Long>{

	// lista departamentos ordenados por id ascendente
	 public List<Departamento> findAllByOrderByIdAsc();


	 
	
}
