package com.edvaldo.leite.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edvaldo.leite.controller.utils.PaginacaoUtil;
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

    // atualizar
    public Cargo atualizar(Cargo obj) {
	Cargo newObj = buscarPorId(obj.getId());
	preAtualizar(newObj, obj);
	return cargoRepository.save(newObj);
    }

    private void preAtualizar(Cargo newObj, Cargo obj) {
	newObj.setNome(obj.getNome());
    }

    public void excluir(Long id) {
	cargoRepository.deleteById(id);
    }

    // buscar por id
    public Cargo buscarPorId(Long id) {
	Optional<Cargo> obj = cargoRepository.findById(id);
	return obj.get();
    }

    public List<Cargo> buscarTodos() {
	return cargoRepository.findAll();
    }

    public boolean cargoTemFuncionario(Long id) {
	if (buscarPorId(id).getFuncionarios().isEmpty()) {
	    return false;
	}
	return true;
    }

    public Page<Cargo> buscaPaginada(Pageable pageable){
	return  cargoRepository.findAllByOrderByNomeAsc(pageable);
    }
    
    public Page<Cargo> buscaPaginadaAsc(Pageable pageable) {
   	return cargoRepository.findAllByOrderByNomeAsc(pageable);
       }
    
    public Page<Cargo> buscaPaginadaDesc(Pageable pageable) {
	return cargoRepository.findAllByOrderByNomeDesc(pageable);
    }

    

}
