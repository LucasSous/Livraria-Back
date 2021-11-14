package com.livraria.apirest.services;

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
		Livro saveLivro = livroRepository.save(livro);
		return saveLivro;
	}
	
	public void deletarLivro(Livro livro) {
		try {
			livroRepository.delete(livro);
		} catch (DataIntegrityViolationException e) {
			throw new com.livraria.apirest.services.excepitions.DataIntegrityViolationException(
					"Este Livro não pode ser deletado! Possui associação com Alugueis.");
		}
	}
	
	public Livro alterarLivro (Livro livro) {
		Livro atualizarLivro = livroRepository.save(livro);
		return atualizarLivro;
	}

}
