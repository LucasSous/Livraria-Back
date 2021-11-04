package com.livraria.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.livraria.apirest.models.Usuario;
import com.livraria.apirest.services.UsuarioService;

@RestController
@RequestMapping(value="/api")
@CrossOrigin(origins = "http://localhost:8081")
public class UsuarioController {
	
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public List<Usuario> listaUsuarios(){
		return usuarioService.todosUsuarios();
	}
	
	@GetMapping("/usuario/{id}")
	public Usuario listaUsuarioUnico(@PathVariable(value="id") long id){
		return usuarioService.buscarPorId(id);
	}
	
	@PostMapping("/usuario")
	public Usuario salvaUsuario(@RequestBody @Valid Usuario usuario) {
		return usuarioService.salvarUsuario(usuario);
	}
	
	@DeleteMapping("/usuario")
	public void deletaUsuario(@RequestBody @Valid Usuario usuario) {
		usuarioService.deletarUsuario(usuario);
	}
	
	@PutMapping("/usuario")
	public Usuario atualizaUsuario(@RequestBody @Valid Usuario usuario) {
		return usuarioService.alterarUsuario(usuario);
	}
	
	
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
		
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError)error).getField();
			String errorMessage = error.getDefaultMessage();
			
			errors.put(fieldName, errorMessage);
		});
		return errors;
		
	}

}
