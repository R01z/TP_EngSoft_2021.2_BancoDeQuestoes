package com.bancodequestoes.model.dao;

import com.bancodequestoes.model.entities.Tema;

import java.util.List;

public interface TemaDao {
	void insert(Tema obj);
	void deleteById(Integer id);
	Tema findById(Integer id);
	List<Tema> findTemas();
	Tema findByNome(String nome);
}
