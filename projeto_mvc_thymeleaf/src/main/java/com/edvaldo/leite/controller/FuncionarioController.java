package com.edvaldo.leite.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
	model.addAttribute("funcionario", funService.buscarPorId(id));
	return "/funcionario/cadastro";
    }

    @PostMapping("/editar")
    public String editar(Funcionario funcionario, RedirectAttributes attr) {
	funService.atualizar(funcionario);
	attr.addFlashAttribute("success", "Funcionário editado com sucesso");
	return "redirect:/funcionarios/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model) {
	funService.excluir(id);
	model.addAttribute("success", "Funcionário excluído com sucesso!");
	return listar(model);
    }

    @GetMapping("/buscar/nome")
    public String getNome(@RequestParam("nome") String nome, ModelMap model) {
	model.addAttribute("funcionarios", funService.buscarPorNome(nome));
	return "/funcionario/lista";
    }

    @GetMapping("/buscar/cargo")
    public String getCargo(@RequestParam("nome") String nome, ModelMap model) {
	model.addAttribute("funcionarios", funService.buscarPorCargo(nome));
	return "/funcionario/lista";
    }

    @GetMapping("/buscar/data")
    public String getData(@RequestParam(name = "entrada" , required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entrada,
	    @RequestParam(name = "saida", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate saida, ModelMap model) {

	model.addAttribute("funcionarios", funService.buscarPorData(entrada, saida));
	return "/funcionario/lista";
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
