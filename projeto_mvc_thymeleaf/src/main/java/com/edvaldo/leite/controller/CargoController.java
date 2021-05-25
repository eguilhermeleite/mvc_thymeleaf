package com.edvaldo.leite.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edvaldo.leite.controller.utils.PaginacaoUtil;
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

    /*
     * @GetMapping("/listar") public String listar(ModelMap md) {
     * md.addAttribute("cargos", crgService.buscarTodos()); return "/cargo/lista"; }
     */
    /*
     * // busca paginada
     * 
     * @GetMapping("/listar") public String listar(@PageableDefault(size = 5)
     * Pageable pageable, ModelMap model) { Page<Cargo> pagina =
     * crgService.buscaPaginada(pageable); model.addAttribute("cargos", pagina);
     * return "/cargo/lista"; }
     */
    @GetMapping("/listar")
    public String listar(ModelMap md, @RequestParam("page") Optional<Integer> page,
	    @RequestParam("size") Optional<Integer> size) {
	int currentPage = page.orElse(1);
	int pageSize = size.orElse(5);
	Page<Cargo> cargoPage = crgService.buscaPaginada(PageRequest.of(currentPage - 1, pageSize));
	md.addAttribute("cargoPage", cargoPage);

	int totalPages = cargoPage.getTotalPages();
	
	if (totalPages > 0) {
	    List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
	    md.addAttribute("pageNumbers", pageNumbers);

	}
	return "/cargo/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {

	if (result.hasErrors()) {
	    return "/cargo/cadastro";
	}

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
	return "/cargo/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {

	if (result.hasErrors()) {
	    return "/cargo/cadastro";
	}

	crgService.atualizar(cargo);
	attr.addFlashAttribute("success", "Cargo editado com sucesso");
	return "redirect:/cargos/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model) {
	Optional<Integer> currentPage = Optional.of(1);
	Optional<Integer> pageSize = Optional.of(5);
	if (crgService.cargoTemFuncionario(id)) {
	    model.addAttribute("fail", "Cargo não removido pois tem funcionário(s) vinculado(s)...");
	    
	} else {
	    crgService.excluir(id);
	    model.addAttribute("success", "Cargo excluído com sucesso!");
	}
	
	//return "redirect:/cargos/listar";
	return listar(model, currentPage, pageSize);
    }

}
