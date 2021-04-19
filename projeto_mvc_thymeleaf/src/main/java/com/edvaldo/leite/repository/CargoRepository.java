package com.edvaldo.leite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edvaldo.leite.domain.Cargo;

@Repository
public interface CargoRepository  extends JpaRepository<Cargo, Long>{

}
