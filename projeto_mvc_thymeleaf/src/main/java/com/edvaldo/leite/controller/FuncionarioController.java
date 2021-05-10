package com.edvaldo.leite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edvaldo.leite.domain.Cargo;
import com.edvaldo.leite.domain.Funcionario;
import com.edvaldo.leite.domain.enums.UF;
import com.edvaldo.leite.service.CargoService;
import com.edvaldo.leite.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funService;

    @Autowired
    private CargoService carService;

    @GetMapping("/cadastrar")
    public String cadastrar(Funcionario funcionario) {
	return "/funcionario/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
	model.addAttribute("funcionarios", funService.buscarTodos());
	return "/funcionario/lista";
    }

    @PostMapping("/salvar")
    public String salvar(Funcionario funcionario, RedirectAttributes attr) {
	funService.salvar(funcionario);
	attr.addFlashAttribute("success", "Funcionário cadastrado com sucesso!");
	return "redirect:/funcionarios/cadastrar";
    }

    @ModelAttribute("cargos")
    public List<Cargo> getCargo() {
	return carService.buscarTodos();
    }
    
    @ModelAttribute("ufs")
    public UF[] getUFs() {
	return UF.values();
    }
}
