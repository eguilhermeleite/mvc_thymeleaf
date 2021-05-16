package com.edvaldo.leite.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edvaldo.leite.domain.Funcionario;
import com.edvaldo.leite.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    public FuncionarioRepository funcionarioRepository;

    public void salvar(Funcionario funcionario) {
	funcionarioRepository.save(funcionario);
    }

    // atualizar
    public Funcionario atualizar(Funcionario obj) {
	Funcionario newObj = buscarPorId(obj.getId());
	preAtualizar(newObj, obj);
	return funcionarioRepository.save(newObj);
    }

    private void preAtualizar(Funcionario newObj, Funcionario obj) {
	newObj.setNome(obj.getNome());
	newObj.setSalario(obj.getSalario());
	newObj.setCargo(obj.getCargo());
	newObj.setDataEntrada(obj.getDataEntrada());
	newObj.setDataSaida(obj.getDataSaida());
	newObj.setEndereco(obj.getEndereco());
    }

    public void excluir(Long id) {
	funcionarioRepository.deleteById(id);
    }

    public Funcionario buscarPorId(Long id) {
	Optional<Funcionario> obj = funcionarioRepository.findById(id);
	return obj.get();

    }

    public List<Funcionario> buscarPorNome(String nome) {
	return funcionarioRepository.findByNomeContaining(nome);
    }

    public List<Funcionario> buscarTodos() {
	return funcionarioRepository.findAll();
    }

    public List<Funcionario> buscarPorCargo(String nome) {

	return funcionarioRepository.findByCargoNome(nome);
    }

    public List<Funcionario> buscarPorData(LocalDate entrada, LocalDate saida) {
	if (entrada != null && saida != null) {
	    return funcionarioRepository.findByDataEntradaBetween(entrada, saida);
	} else if (saida == null) {
	    return funcionarioRepository.findByDataEntrada(entrada);
	} else if (entrada == null) {
	    return funcionarioRepository.findByDataSaida(saida);
	} else {
	    return new ArrayList<>();
	}
    }

}
