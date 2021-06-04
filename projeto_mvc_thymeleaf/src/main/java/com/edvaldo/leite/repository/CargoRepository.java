package com.edvaldo.leite.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edvaldo.leite.controller.utils.PaginacaoUtil;
import com.edvaldo.leite.domain.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

    Page<Cargo> findAllByOrderByNomeDesc(Pageable pageable);

    Page<Cargo> findAllByOrderByNomeAsc(Pageable pageable);

    Page<Cargo> findAll(Pageable pageable);

   
}
