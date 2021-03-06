package com.edvaldo.leite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String listar(ModelMap model) {
		model.addAttribute("departamentos", service.buscarTodos());
		return "/departamento/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Departamento departamento, BindingResult result,
			RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "/departamento/cadastro";
		}

		service.salvar(departamento);
		attr.addFlashAttribute("success",
				"Departamento cadastrado com sucesso");
		return "redirect:/departamentos/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("departamento", service.buscarPorId(id));
		return "/departamento/cadastro";
	}

	@PostMapping("/editar")
	public String editar(Departamento departamento, RedirectAttributes attr) {
		service.atualizar(departamento);
		attr.addFlashAttribute("success", "Departamento editado com sucesso");
		return "redirect:/departamentos/cadastrar";
	}

	// OPCIONAL
	@GetMapping("/confirmarExclusao/{id}")
	public String confirmarExclusao() {
		return "redirect:/departamentos/listar";
	}
	// ***********************

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		if (service.departamentoTemCargo(id)) {
			model.addAttribute("fail",
					"Departamento n??o removido pois tem cargo(s) vinculado(s)...");
		} else {
			service.excluir(id);
			model.addAttribute("success", "Departamento exclu??do com sucesso!");
		}
		return listar(model);
	}

}// fim da classe
