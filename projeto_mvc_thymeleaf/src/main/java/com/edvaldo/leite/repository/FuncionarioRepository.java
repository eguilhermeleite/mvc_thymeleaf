package com.edvaldo.leite.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edvaldo.leite.domain.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    List<Funcionario> findByNomeContaining(String nome);

    List<Funcionario> findByCargoNome(String nome);
    
    List<Funcionario> findByDataEntradaBetween(LocalDate dataEntrada, LocalDate dataSaida);

    List<Funcionario> findByDataEntrada(LocalDate dataEntrada);

    List<Funcionario> findByDataSaida(LocalDate dataSaida);

   
}
