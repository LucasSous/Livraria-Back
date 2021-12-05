package com.livraria.apirest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.livraria.apirest.models.Aluguel;
import com.livraria.apirest.models.Livro;
import com.livraria.apirest.repositorys.AluguelRepository;
import com.livraria.apirest.repositorys.LivroRepository;

@Service
public class AluguelService {

	@Autowired
	private AluguelRepository aluguelRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	
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
		
		Livro livro = livroRepository.findById(aluguel.getLivro_id().getId());
		
		if(aluguel.getData_previsao().isBefore(aluguel.getData_aluguel())) {
			throw new com.livraria.apirest.services.excepitions.DataIntegrityViolationException(
					"A data de previsão não pode ser menor que a data de aluguel!");
		}else {
		
			if(livro.getQuantidade() > 0) {
				livro.setQuantidade(livro.getQuantidade() - 1);
				livro.setTotalalugado(livro.getTotalalugado() + 1);
				
			    livroRepository.save(livro);
	
				Aluguel saveAluguel = aluguelRepository.save(aluguel);
				return saveAluguel;
				
			}
			else {
				throw new com.livraria.apirest.services.excepitions.DataIntegrityViolationException(
						"Este Livro está indisponível.");
			}
		}
	}
	
	public void deletarAluguel(Aluguel aluguel) {
		
		Livro livro = livroRepository.findById(aluguel.getLivro_id().getId());
		if(aluguel.getData_devolucao() != null) {
			throw new com.livraria.apirest.services.excepitions.DataIntegrityViolationException(
					"Este aluguel não pode ser deletado");
		}else {
		livro.setTotalalugado(livro.getTotalalugado() - 1);
		aluguelRepository.delete(aluguel);
		}
	}
	
	public Aluguel alterarAluguel (Aluguel aluguel) {
		
		Livro livro = livroRepository.findById(aluguel.getLivro_id().getId());
		
		if(aluguel.getData_devolucao().isBefore(aluguel.getData_aluguel())) {
			throw new com.livraria.apirest.services.excepitions.DataIntegrityViolationException(
					"A data de devolução não pode ser menor que a data de aluguel!");
		}else {
		
			if(aluguel.getData_devolucao() != null) {
				livro.setQuantidade(livro.getQuantidade() + 1);
			}
			
			Aluguel atualizarAluguel = aluguelRepository.save(aluguel);
			return atualizarAluguel;
		}
	}
	
	
}
