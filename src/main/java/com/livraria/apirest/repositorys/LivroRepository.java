package com.livraria.apirest.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livraria.apirest.models.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
	
	
	Livro findById(long id);

}
