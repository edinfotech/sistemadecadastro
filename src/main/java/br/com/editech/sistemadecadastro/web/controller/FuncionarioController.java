package br.com.editech.sistemadecadastro.web.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.editech.sistemadecadastro.domain.Cargo;
import br.com.editech.sistemadecadastro.domain.Funcionario;
import br.com.editech.sistemadecadastro.domain.UF;
import br.com.editech.sistemadecadastro.service.CargoService;
import br.com.editech.sistemadecadastro.service.FuncionarioService;
import br.com.editech.sistemadecadastro.web.validator.FuncionarioValidator;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcService;
	@Autowired
	private CargoService cargoService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new FuncionarioValidator());
	}
	
	@GetMapping("/cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "funcionario/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("funcionarios",funcService.buscarTodos());
		
		return "funcionario/listar";
	}
	
	@PostMapping("/salvar")
	public String salvar(Funcionario funcionario, RedirectAttributes attr) {
		funcService.salvar(funcionario);
		attr.addFlashAttribute("success","Funcionario inserido com sucesso.");
		return "redirect:/funcionarios/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("funcionario", funcService.buscarPorId(id));
		return "funcionario/cadastro";
	}
	@PostMapping("/editar")
	public  String editar(@Valid Funcionario funcionario,BindingResult result, RedirectAttributes attr) {
		if(result.hasErrors()) {
			return "funcionario/cadastro";
		}
		
		funcService.editar(funcionario);
		attr.addFlashAttribute("success","Funcionario editado com sucesso.");
		return "redirect:/funcionarios/cadastrar";
		
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		funcService.excluir(id);
			attr.addFlashAttribute("success" , "Funcionario excluido com sucesso.");
		
		return "redirect:/funcionarios/listar";
	}
	 
	@GetMapping("/buscar/nome")
	public String getporNome(@RequestParam("nome") String nome,ModelMap model) {
		model.addAttribute("funcionarios", funcService.buscarPorNome(nome));
		return "funcionario/listar";
		
	}
	
	@GetMapping("/buscar/cargo")
	public String getporCargo(@RequestParam("id") Long id,ModelMap model) {
		model.addAttribute("funcionarios", funcService.buscarPorCargo(id));
		return "funcionario/listar";
		
	}
	@GetMapping("/buscar/data")
	public String getporDatas(@RequestParam("entrada") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entrada ,
							  @RequestParam("saida") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate saida,
							  ModelMap model) {
		model.addAttribute("funcionarios", funcService.buscarPorDatas(entrada, saida));
		return "funcionario/listar";
	}
	
	@ModelAttribute("cargos")
	public List<Cargo> getCargos(){
		return cargoService.buscarTodos();
	}
	@ModelAttribute("ufs")
	public UF[] getUFs() {
		return UF.values();
	}
}
