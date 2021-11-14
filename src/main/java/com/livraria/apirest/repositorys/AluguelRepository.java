package com.livraria.apirest.repositorys;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livraria.apirest.models.Aluguel;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long>{
	
	Aluguel findById(long id);

}
