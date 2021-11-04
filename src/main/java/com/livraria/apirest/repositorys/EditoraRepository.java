package com.livraria.apirest.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livraria.apirest.models.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {

	Editora findById(long id);
	
}
