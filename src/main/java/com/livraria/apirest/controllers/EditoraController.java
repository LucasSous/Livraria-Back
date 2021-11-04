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

import com.livraria.apirest.models.Editora;
import com.livraria.apirest.services.EditoraService;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "http://localhost:8081")
public class EditoraController {

	@Autowired
	private EditoraService editoraService;
	
	@GetMapping("/editoras")
	public List<Editora> listaEditora(){
		return editoraService.todasEditoras();
	}
	
	@GetMapping("/editora/{id}")
	public Editora listaEditoraUnico(@PathVariable(value="id") long id){
		return editoraService.busarPorId(id);
	}
	
	@PostMapping("/editora")
	public Editora salvaEditora(@RequestBody @Valid Editora editora) {
		return editoraService.salvaEditora(editora);
	}
	
	@DeleteMapping("/editora")
	public void deletaEditora(@RequestBody @Valid Editora editora) {
		editoraService.deletarEditora(editora);
	}
	
	@PutMapping("/editora")
	public Editora atualizaEditora(@RequestBody @Valid Editora editora) {
		return editoraService.alterarEditora(editora);
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
