package com.edvaldo.leite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edvaldo.leite.domain.Funcionario;
import com.edvaldo.leite.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	public FuncionarioRepository funcionarioRepository;

	public void salvar(Funcionario Funcionario) {
		funcionarioRepository.save(Funcionario);
	}

	public void editar(Funcionario Funcionario) {

	}

	public void excluir(Long id) {
		funcionarioRepository.deleteById(id);
	}

	public void buscarPorId(Long id) {
		funcionarioRepository.findById(id);
	}

	public List<Funcionario> buscarTodos() {
		return funcionarioRepository.findAll();
	}

}
