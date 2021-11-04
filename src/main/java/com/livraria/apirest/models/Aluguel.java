package com.livraria.apirest.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="TB_ALUGUEL")
public class Aluguel{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="Campo não informado!")
	@OneToOne
	private Livro livro;
	
	@NotNull(message="Campo não informado!")
	@OneToOne
	private Usuario usuario;
	
	@NotNull(message="Campo não informado!")
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	//@DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd-MM-yyyy")
	private LocalDate data_aluguel;
	
	@NotNull(message="Campo não informado!")
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	//@DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd-MM-yyyy")
	private LocalDate data_previsao;
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	//@DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd-MM-yyyy")
	private LocalDate data_devolucao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Livro getLivro_id() {
		return livro;
	}

	public void setLivro_id(Livro livro_id) {
		this.livro = livro_id;
	}

	public Usuario getUsuario_id() {
		return usuario;
	}

	public void setUsuario_id(Usuario usuario_id) {
		this.usuario = usuario_id;
	}

	public LocalDate getData_aluguel() {
		return data_aluguel;
	}

	public void setData_aluguel(LocalDate data_aluguel) {
		this.data_aluguel = data_aluguel;
	}

	public LocalDate getData_previsao() {
		return data_previsao;
	}

	public void setData_previsao(LocalDate data_previsao) {
		this.data_previsao = data_previsao;
	}

	public LocalDate getData_devolucao() {
		return data_devolucao;
	}

	public void setData_devolucao(LocalDate data_devolucao) {
		this.data_devolucao = data_devolucao;
	}
	
	

}
