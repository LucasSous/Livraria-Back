package com.livraria.apirest.services;

import java.time.LocalDate;
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
		
		LocalDate dataatual = LocalDate.now();
		
		aluguel.setData_devolucao(null);
		
		if(aluguel.getData_previsao().isBefore(aluguel.getData_aluguel())) {
			throw new com.livraria.apirest.services.excepitions.DataIntegrityViolationException(
					"A data de previsão não pode ser menor que a data de aluguel!");
		}else if(aluguel.getData_aluguel().isAfter(dataatual) || aluguel.getData_aluguel().isBefore(dataatual)){
			throw new com.livraria.apirest.services.excepitions.DataIntegrityViolationException(
					"A data de aluguel deve ser a data atual: " + dataatual );
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
		livro.setQuantidade(livro.getQuantidade() +1);
		aluguelRepository.delete(aluguel);
		}
	}
	
	public Aluguel alterarAluguel (Aluguel aluguel) {
		
		Livro livro = livroRepository.findById(aluguel.getLivro_id().getId());
		
		LocalDate dataatual = LocalDate.now();
		
		if(aluguel.getData_devolucao().isBefore(aluguel.getData_aluguel())) {
			throw new com.livraria.apirest.services.excepitions.DataIntegrityViolationException(
					"A data de devolução não pode ser menor que a data de aluguel!");
		}else if(aluguel.getData_devolucao().isAfter(dataatual) || aluguel.getData_devolucao().isBefore(dataatual)){
			throw new com.livraria.apirest.services.excepitions.DataIntegrityViolationException(
					"A data de devolução deve ser a atual: " + dataatual);
		}else {
		
			if(aluguel.getData_devolucao() != null) {
				livro.setQuantidade(livro.getQuantidade() + 1);
			}
			
			Aluguel atualizarAluguel = aluguelRepository.save(aluguel);
			return atualizarAluguel;
		}
	}
	
	
}
