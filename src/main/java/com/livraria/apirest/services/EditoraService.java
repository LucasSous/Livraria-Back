package com.livraria.apirest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.livraria.apirest.models.Editora;
import com.livraria.apirest.repositorys.EditoraRepository;

@Service
public class EditoraService {
	
	@Autowired
	private EditoraRepository editoraRepository;
	
	public List<Editora> todasEditoras(){
		List<Editora> listEditoras = editoraRepository.findAll();
		return listEditoras;
	} 
	
	public Editora busarPorId (Long id) {
		Optional<Editora> optionalEditora = editoraRepository.findById(id);
		Editora editora = null;
		editora = optionalEditora.get();
		return editora;
	}
	
	public Editora salvaEditora(Editora editora) {
		Editora saveEditora = editoraRepository.save(editora);
		return saveEditora;
	}
	
	public void deletarEditora (Editora editora) {
		try {
			editoraRepository.delete(editora);
		} catch (DataIntegrityViolationException e) {
			throw new com.livraria.apirest.services.excepitions.DataIntegrityViolationException(
					"Esta Editora não pode ser deletada! Possui livros associados.");
		}
	}
	
	public Editora alterarEditora(Editora editora) {
		Editora atualizarEditora = editoraRepository.save(editora);
		return atualizarEditora;
	}

}
