package com.edvaldo.leite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edvaldo.leite.domain.Departamento;
import com.edvaldo.leite.repository.DepartamentoRepository;

@Service
@Transactional
public class DepartamentoService {

	@Autowired
	public DepartamentoRepository departamentoRepository;

	public void salvar(Departamento Departamento) {
		departamentoRepository.save(Departamento);
	}

	public void editar(Departamento Departamento) {

	}

	public void excluir(Long id) {
		departamentoRepository.deleteById(id);
	}

	public void buscarPorId(Long id) {
		departamentoRepository.findById(id);
	}

	public List<Departamento> buscarTodos() {
		return departamentoRepository.findAll();
	}

}
