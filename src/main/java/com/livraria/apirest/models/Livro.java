package com.livraria.apirest.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



@Entity
@Table(name="TB_LIVRO")
public class Livro implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Campo não informado!")
	private String nome;
	
	@OneToOne
	private Editora editora;
	
	@NotBlank(message = "Campo não informado!")
	private String autor;
	
	@Digits(integer = 4, fraction = 4, message = "o ano deve ter 4 digitos")
	@NotNull(message = "Campo não preenchido, informe o ano de lançamento do livro!")
	private int lancamento;
	
	
	@NotNull(message = "Campo não informado!")
	private int quantidade;
	
	private int totalalugado;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getLancamento() {
		return lancamento;
	}

	public void setLancamento(int lancamento) {
		this.lancamento = lancamento;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getTotalalugado() {
		return totalalugado;
	}

	public void setTotalalugado(int totalalugado) {
		this.totalalugado = totalalugado;
	}

	
	
	

}
