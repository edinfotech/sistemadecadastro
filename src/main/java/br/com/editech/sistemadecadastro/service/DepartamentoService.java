package br.com.editech.sistemadecadastro.service;

import java.util.List;

import br.com.editech.sistemadecadastro.domain.Departamento;

public interface DepartamentoService {

	void salvar(Departamento departamento);
	void editar(Departamento departamento);
	void excluir(Long id);
	Departamento buscarporId(Long id);
	
	List<Departamento> buscarTodos();
	
	boolean departamentoTemCargos(Long id);
}
