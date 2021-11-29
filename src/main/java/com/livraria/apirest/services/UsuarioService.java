package com.livraria.apirest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.livraria.apirest.models.Usuario;
import com.livraria.apirest.repositorys.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> todosUsuarios(){
		List<Usuario> listUsuarios = usuarioRepository.findAll();
		return listUsuarios;
	}
	
	public Usuario buscarPorId(Long id) {
		Optional<Usuario> opitionalUsuario = usuarioRepository.findById(id);
		Usuario usuario = null;
		usuario = opitionalUsuario.get();
		return usuario;
	}
	
	public Usuario salvarUsuario(Usuario usuario) {
		
		try {
			Usuario saveUsuario = usuarioRepository.save(usuario);
			return saveUsuario;
		} catch (DataIntegrityViolationException e) {
			throw new com.livraria.apirest.services.excepitions.DataIntegrityViolationException(
					"Este endereço de e-mail já existe no sistema!");
		}
		
	}
	
	public void deletarUsuario(Usuario usuario) {
		try {
			usuarioRepository.delete(usuario);
		} catch (DataIntegrityViolationException e) {
			throw new com.livraria.apirest.services.excepitions.DataIntegrityViolationException(
					"Este Usuario não pode ser deletado! Possui associação com Alugueis.");
		}
	}
	
	public Usuario alterarUsuario (Usuario usuario) {
		try {
			Usuario atualizarUsuario = usuarioRepository.save(usuario);
			return atualizarUsuario;
		} catch (DataIntegrityViolationException e) {
			throw new com.livraria.apirest.services.excepitions.DataIntegrityViolationException(
					"Este endereço de e-mail já existe no sistema!");
		}
	}
	
}
