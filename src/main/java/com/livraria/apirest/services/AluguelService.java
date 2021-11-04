package com.livraria.apirest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livraria.apirest.models.Aluguel;
import com.livraria.apirest.repositorys.AluguelRepository;

@Service
public class AluguelService {

	@Autowired
	private AluguelRepository aluguelRepository;
	
	public List<Aluguel> todosAlugueis(){
		List<Aluguel> listAlugueis = aluguelRepository.findAll();
		return listAlugueis;
	}
	
	public Aluguel buscarPorId(Long id) {
		Optional<Aluguel> opitionalAluguel = aluguelRepository.findById(id);
		Aluguel aluguel = null;
		aluguel = opitionalAluguel.get();
		return aluguel;
	}
	
	public Aluguel salvarAluguel(Aluguel aluguel) {
		Aluguel saveAluguel = aluguelRepository.save(aluguel);
		return saveAluguel;
	}
	
	public void deletarAluguel(Aluguel aluguel) {
		aluguelRepository.delete(aluguel);
	}
	
	public Aluguel alterarAluguel (Aluguel aluguel) {
		Aluguel atualizarAluguel = aluguelRepository.save(aluguel);
		return atualizarAluguel;
	}
	
	
}
