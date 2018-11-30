package br.com.editech.sistemadecadastro.dao;

import java.util.List;

import br.com.editech.sistemadecadastro.domain.Cargo;

public interface CargoDao {

	void save(Cargo cargo);
	void update(Cargo cargo);
	void delete(Long id);
	Cargo findById(Long id);
	
	List<Cargo> findAll();
}
