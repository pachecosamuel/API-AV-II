package com.residencia.academia.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "instrutor")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idInstrutor")
public class Instrutor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_instrutor")
	private Integer idInstrutor;

	@Max(value = 9, message = "Campo deve conter entre 7 e 9 dígitos")
	@Column(name = "rg")
	private Integer rg;

	@Column(name = "nome")
	@NotBlank(message = "Nome não informado.")
	@Pattern(regexp = "^[A-Z]+(.)*", message = "Primeira letra do nome deve ser maiúscula")
	private String nomeInstrutor;

	@Column(name = "nascimento")
	@NotNull
	@Past(message = "Data de abertura do Instrutor não pode ser no futuro.")
	private Date dataNascimento;

	@Column(name = "titulacao")
	@NotNull(message = "Insira uma titulação válida.")
	private Integer titulacaoInstrutor;

	@Column(name = "foto")
	private String fotoInstrutor;

	@OneToMany(mappedBy = "instrutor")
	private List<Turma> turmaList;

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/*
	 * public List<Turma> getTurmaList() { return turmaList; }
	 * 
	 * public void setTurmaList(List<Turma> turmaList) { this.turmaList = turmaList;
	 * }
	 */

	public Integer getIdInstrutor() {
		return idInstrutor;
	}

	public void setIdInstrutor(Integer idInstrutor) {
		this.idInstrutor = idInstrutor;
	}

	public Integer getRg() {
		return rg;
	}

	public void setRg(Integer rg) {
		this.rg = rg;
	}

	public String getNomeInstrutor() {
		return nomeInstrutor;
	}

	public void setNomeInstrutor(String nomeInstrutor) {
		this.nomeInstrutor = nomeInstrutor;
	}

	public Integer getTitulacaoInstrutor() {
		return titulacaoInstrutor;
	}

	public void setTitulacaoInstrutor(Integer titulacaoInstrutor) {
		this.titulacaoInstrutor = titulacaoInstrutor;
	}

	public String getFotoInstrutor() {
		return fotoInstrutor;
	}

	public void setFotoInstrutor(String fotoInstrutor) {
		this.fotoInstrutor = fotoInstrutor;
	}

	public List<Turma> getTurmaList() {
		return turmaList;
	}

	public void setTurmaList(List<Turma> turmaList) {
		this.turmaList = turmaList;
	}

}
