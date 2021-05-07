package com.edvaldo.leite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edvaldo.leite.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
    @Autowired
    private FuncionarioService funService;
	
	@GetMapping("/cadastrar")
	public String cadastrar() {
		return "/funcionario/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
	    model.addAttribute("funcionarios", funService.buscarTodos());
		return "/funcionario/lista";
	}
	
	

}
