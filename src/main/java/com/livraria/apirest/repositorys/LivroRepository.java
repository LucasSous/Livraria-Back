package com.livraria.apirest.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.livraria.apirest.models.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
	
	
	Livro findById(long id);
	
	@Query("select a from Livro a where a.quantidade != 0")
	List<Livro> findList();
	
	@Query("select l from Livro l order by l.totalalugado desc")
	List<Livro> findLivros();

}
