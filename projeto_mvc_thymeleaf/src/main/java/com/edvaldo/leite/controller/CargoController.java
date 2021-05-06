package com.edvaldo.leite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edvaldo.leite.domain.Cargo;
import com.edvaldo.leite.domain.Departamento;
import com.edvaldo.leite.service.CargoService;
import com.edvaldo.leite.service.DepartamentoService;

@Controller
@RequestMapping("/cargos")
public class CargoController {

	@Autowired
	private CargoService crgService;

	@Autowired
	private DepartamentoService depService;
	
	

	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {
		return "/cargo/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap md) {
		md.addAttribute("cargos", crgService.buscarTodos());
		return "/cargo/lista";
	}

	@PostMapping("/salvar")
	public String salvar(Cargo cargo, RedirectAttributes attr) {
		crgService.salvar(cargo);
		attr.addFlashAttribute("success", "Cargo cadastrado com sucesso");
		return "redirect:/cargos/cadastrar";
	}

	@ModelAttribute("departamentos")
	public List<Departamento> listaDeDepartamentos() {
		return depService.buscarTodos();
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cargo", crgService.buscarPorId(id));
		return "/cargo/editaCargo";
	}

	@PostMapping("/editar")
	public String editar(Cargo cargo, RedirectAttributes attr) {
		crgService.atualizar(cargo);
		attr.addFlashAttribute("success", "Cargo editado com sucesso");
		return "redirect:/cargos/cadastrar";
	}
	
	 @GetMapping("/excluir/{id}")
	    public String excluir(@PathVariable("id") Long id, ModelMap model) {
		if (crgService.cargoTemFuncionario(id)) {
		    model.addAttribute("fail", "Cargo não removido pois tem funcionário(s) vinculado(s)...");
		} else {
		    crgService.excluir(id);
		    model.addAttribute("success", "Cargo excluído com sucesso!");
		}
		return listar(model);
	    }
}
