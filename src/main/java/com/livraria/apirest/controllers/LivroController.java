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

import com.livraria.apirest.models.Livro;
import com.livraria.apirest.services.LivroService;


@RestController
@RequestMapping(value="/api")
@CrossOrigin(origins = "*")
public class LivroController {
	
	@Autowired
	private LivroService livroService;
	
	@GetMapping("/livros")
	public List<Livro> listaLivros(){
		return livroService.todosLivros();
	}
	
	@GetMapping("/disponiveis")
	public List<Livro> listaDisponiveis(){
		return livroService.livrosDisponiveis();
	}
	
	@GetMapping("/maisalugados")
	public List<Livro> listaMaisAlugados(){
		return livroService.livrosMaisAluados();
	}
	
	@GetMapping("/livro/{id}")
	public Livro listaLivroUnico(@PathVariable(value="id") long id){
		return livroService.buscarPorId(id);
	}
	
	@PostMapping("/livro")
	public Livro salvaLivro(@RequestBody @Valid Livro livro) {
		return livroService.salvarLivro(livro);
	}
	
	@DeleteMapping("/livro")
	public void deletaLivro(@RequestBody @Valid Livro livro) {
		livroService.deletarLivro(livro);
	}
	
	@PutMapping("/livro")
	public Livro atualizaLivro(@RequestBody @Valid Livro livro) {
		return livroService.alterarLivro(livro);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
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
