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

import com.livraria.apirest.models.Aluguel;
import com.livraria.apirest.services.AluguelService;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "http://localhost:8081")
public class AluguelController {

	@Autowired
	private AluguelService aluguelService;
	
	@GetMapping("/alugueis")
	public List<Aluguel> listaAluguel(){
		return aluguelService.todosAlugueis();
	}
	
	@GetMapping("/aluguel/{id}")
	public Aluguel listaAluguelUnico(@PathVariable(value="id") long id){
		return aluguelService.buscarPorId(id);
	}
	
	@PostMapping("/aluguel")
	public Aluguel salvaAluguel(@RequestBody @Valid Aluguel aluguel) {
		return aluguelService.salvarAluguel(aluguel);
	}
	
	@DeleteMapping("/aluguel")
	public void deletaAluguel(@RequestBody @Valid Aluguel aluguel) {
		aluguelService.deletarAluguel(aluguel);
	}
	
	@PutMapping("/aluguel")
	public Aluguel atualizaAluguel(@RequestBody @Valid Aluguel aluguel) {
		return aluguelService.alterarAluguel(aluguel);
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
