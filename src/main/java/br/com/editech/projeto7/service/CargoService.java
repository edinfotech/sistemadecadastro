package br.com.editech.projeto7.service;

import java.util.List;

import br.com.editech.projeto7.domain.Cargo;

public interface CargoService {
	

	void salvar(Cargo cargo);
	void editar(Cargo cargo);
	void excluir(Long id);
	Cargo buscarPorId(Long id);
	
	List<Cargo> buscarTodos();
	
	boolean cargoTemFuncionarios(Long id);

}
