package com.edvaldo.leite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edvaldo.leite.domain.Funcionario;

@Repository
public interface FuncionarioRepository  extends JpaRepository<Funcionario, Long>{

}
