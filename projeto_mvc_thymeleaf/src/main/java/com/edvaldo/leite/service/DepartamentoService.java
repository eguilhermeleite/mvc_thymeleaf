package com.edvaldo.leite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edvaldo.leite.domain.Departamento;
import com.edvaldo.leite.repository.DepartamentoRepository;

@Service
@Transactional
public class DepartamentoService {

    // instância para métodos de crud
    @Autowired
    public DepartamentoRepository departamentoRepository;

    // ************Implementação dos métodos********

    // salvar
    public void salvar(Departamento departamento) {
	departamentoRepository.save(departamento);
    }

    // excluir
    public void excluir(Long id) {
	departamentoRepository.deleteById(id);
    }

    // buscar por id
    public Departamento buscarPorId(Long id) {
	Optional<Departamento> obj = departamentoRepository.findById(id);
	return obj.get();
    }

    // buscar todos
    public List<Departamento> buscarTodos() {
	return departamentoRepository.findAll();
    }

    // atualizar
    public Departamento atualizar(Departamento obj) {
	Departamento newObj = buscarPorId(obj.getId());
	preAtualizar(newObj, obj);
	return departamentoRepository.save(newObj);
    }

    private void preAtualizar(Departamento newObj, Departamento obj) {
	newObj.setNome(obj.getNome());
    }

    // verifica se departamento tem cargo vinculado
    public boolean departamentoTemCargo(Long id) {
	if (buscarPorId(id).getCargos().isEmpty()) {
	    return false;
	}
	return true;
    }

}
