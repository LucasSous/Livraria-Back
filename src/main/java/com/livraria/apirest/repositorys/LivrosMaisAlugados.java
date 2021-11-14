package com.livraria.apirest.repositorys;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.livraria.apirest.models.Aluguel;

@Repository
public class LivrosMaisAlugados {

	private final EntityManager em;

	public LivrosMaisAlugados(EntityManager em) {
		this.em = em;
	}
	
	public List<Aluguel> findAll(){
		
		String query = "select livro_id, count(*) as quantidade from tb_aluguel group by livro_id order by quantidade desc limit 3";
		
		var q = em.createQuery(query, Aluguel.class);
		
		return q.getResultList();
		
	}
	
}
