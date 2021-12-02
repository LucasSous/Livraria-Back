package com.livraria.apirest.services;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.livraria.apirest.models.Livro;
import com.livraria.apirest.repositorys.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	public List<Livro> todosLivros(){
		List<Livro> listLivros = livroRepository.findAll();
		return listLivros;
	}
	
	public List<Livro> livrosDisponiveis(){
		List<Livro> listDisponiveis = livroRepository.findList();
		return listDisponiveis;
	}
	
	public List<Livro> livrosMaisAluados(){
		List<Livro> listMaisAlugados = livroRepository.findLivros();
		return listMaisAlugados;
	}
	
	public Livro buscarPorId(Long id) {
		Optional<Livro> opitionalLivro = livroRepository.findById(id);
		Livro livro = null;
		livro = opitionalLivro.get();
		return livro;
	}
	
	public Livro salvarLivro(Livro livro) {
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		if(livro.getLancamento() < 999 || livro.getLancamento() > year) {
			throw new com.livraria.apirest.services.excepitions.DataIntegrityViolationException(
					"lançamento não foi informado corretamente.");
		}else if(livro.getQuantidade() <= 0){
			throw new com.livraria.apirest.services.excepitions.DataIntegrityViolationException(
					"Quantidade de livros não pode ser menor que 1.");
		}else if(livro.getTotalalugado() != 0){
			throw new com.livraria.apirest.services.excepitions.DataIntegrityViolationException(
					"A quantidade de total de livros alugados deve iniciar em 0!");
		}else {
		
			try {
				Livro saveLivro = livroRepository.save(livro);
				return saveLivro;
			} catch (Exception e) {
				throw new com.livraria.apirest.services.excepitions.DataIntegrityViolationException(
						"Este livro já existe,tente novamente com outro nome!");
			}
			
		}
		
	}
	
	public void deletarLivro(Livro livro) {
		try {
			livroRepository.delete(livro);
		} catch (DataIntegrityViolationException e) {
			throw new com.livraria.apirest.services.excepitions.DataIntegrityViolationException(
					"Este Livro não pode ser deletado! Possui associação com Aluguéis.");
		}
	}
	
	public Livro alterarLivro (Livro livro) {
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		Livro livroo = livroRepository.findById(livro.getId());
		 
		
		if(livro.getLancamento() < 999 || livro.getLancamento() > year) {
			throw new com.livraria.apirest.services.excepitions.DataIntegrityViolationException(
					"lançamento não foi informado corretamente.");
		}else if(livro.getQuantidade() < 0){
			throw new com.livraria.apirest.services.excepitions.DataIntegrityViolationException(
					"A quantidade de livros não pode ser negativa!");
		}else if(livroo.getTotalalugado() != livro.getTotalalugado()){
			throw new com.livraria.apirest.services.excepitions.DataIntegrityViolationException(
					"O total de livros alugados não pode ser alterado!" + "(" + livroo.getTotalalugado() + ")");
		}else {
			try {
				Livro atualizarLivro = livroRepository.save(livro);
				return atualizarLivro;
			} catch (Exception e) {
				throw new com.livraria.apirest.services.excepitions.DataIntegrityViolationException(
						"Este livro já existe,tente novamente com outro nome!");
			}
		}
		
		
		
	}

}
