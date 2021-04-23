package com.edvaldo.leite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edvaldo.leite.domain.Departamento;
import com.edvaldo.leite.service.DepartamentoService;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

	@Autowired
	private DepartamentoService service;

	@GetMapping("/cadastrar")
	public String cadastrar(Departamento departamento) {
		return "/departamento/cadastro";
	}

	@GetMapping("/listar")
	public String listar() {
		return "/departamento/lista";
	}

	@PostMapping("/salvar")
	public String salvar(Departamento departamento) {
		service.salvar(departamento);
		return "redirect:/departamentos/cadastrar";
	}

}
