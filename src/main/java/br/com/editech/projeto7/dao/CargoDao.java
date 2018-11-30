package br.com.editech.projeto7.dao;

import java.util.List;

import br.com.editech.projeto7.domain.Cargo;

public interface CargoDao {

	void save(Cargo cargo);
	void update(Cargo cargo);
	void delete(Long id);
	Cargo findById(Long id);
	
	List<Cargo> findAll();
}
