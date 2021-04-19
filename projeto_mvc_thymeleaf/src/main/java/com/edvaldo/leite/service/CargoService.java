package com.edvaldo.leite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edvaldo.leite.domain.Cargo;
import com.edvaldo.leite.repository.CargoRepository;

@Service
@Transactional
public class CargoService {

	@Autowired
	public CargoRepository cargoRepository;

	public void salvar(Cargo cargo) {
		cargoRepository.save(cargo);
	}

	public void editar(Cargo cargo) {

	}

	public void excluir(Long id) {
		cargoRepository.deleteById(id);
	}

	public void buscarPorId(Long id) {
		cargoRepository.findById(id);
	}

	public List<Cargo> buscarTodos() {
		return cargoRepository.findAll();
	}

}
