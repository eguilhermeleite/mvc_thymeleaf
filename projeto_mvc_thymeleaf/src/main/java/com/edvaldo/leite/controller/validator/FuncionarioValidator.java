package com.edvaldo.leite.controller.validator;

import java.time.LocalDate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.edvaldo.leite.domain.Funcionario;

public class FuncionarioValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {

	return Funcionario.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
	Funcionario funcionario = (Funcionario) obj;
	
	LocalDate entrada = funcionario.getDataEntrada();
	
	if (funcionario.getDataSaida() != null) {
	    if(funcionario.getDataSaida().isBefore(entrada)) {
		errors.rejectValue("dataSaida", "PosteriorDataEntrada.funcionario.dataSaida");
	    }
	}
    }

}
