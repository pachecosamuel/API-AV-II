package com.residencia.academia.dto;

import java.util.Date;

public class TurmaDTO {

	private Integer idTurma;

	private Date horarioTurma;

	private Integer duracaoTurma;

	private Date dataInicio;

	private Date dataFim;

//	private Atividade atividade;

	private InstrutorDTO instrutorDTO;

	public Integer getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Integer idTurma) {
		this.idTurma = idTurma;
	}

	public Date getHorarioTurma() {
		return horarioTurma;
	}

	public void setHorarioTurma(Date horarioTurma) {
		this.horarioTurma = horarioTurma;
	}

	public Integer getDuracaoTurma() {
		return duracaoTurma;
	}

	public void setDuracaoTurma(Integer duracaoTurma) {
		this.duracaoTurma = duracaoTurma;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public InstrutorDTO getInstrutorDTO() {
		return instrutorDTO;
	}

	public void setInstrutorDTO(InstrutorDTO instrutorDTO) {
		this.instrutorDTO = instrutorDTO;
	}

}
