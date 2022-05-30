package com.residencia.academia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.academia.dto.AtividadeDTO;
import com.residencia.academia.dto.InstrutorDTO;
import com.residencia.academia.dto.TurmaDTO;
import com.residencia.academia.entity.Atividade;
import com.residencia.academia.entity.Instrutor;
import com.residencia.academia.entity.Turma;
import com.residencia.academia.repository.AtividadeRepository;

@Service
public class AtividadeService {

	@Autowired
	AtividadeRepository atividadeRepository;
	
	@Autowired
	private TurmaService turmaService;
	
	public List<Atividade> findAllAtividade(){
		return atividadeRepository.findAll();
	}
	
	public Atividade findAtividadeById(Integer id) {
		return atividadeRepository.findById(id).isPresent() ?
				atividadeRepository.findById(id).get() : null;
	}
	
	public AtividadeDTO findAtividadeDTOById(Integer id) {
		return atividadeRepository.findById(id).isPresent() ? 
			   converterAtividadeParaDTO(atividadeRepository.findById(id).get()) : null;
	}
	
	public Atividade saveAtividade(Atividade atividade) {
		return atividadeRepository.save(atividade);
	}
	
	public Atividade saveDTO(AtividadeDTO atividadeDTO) {
		Atividade atividade = converterDTOParaAtividade(atividadeDTO);
		return atividadeRepository.save(atividade);
	}

	public Atividade updateAtividade(Atividade atividade) {
		return atividadeRepository.save(atividade);
	}
	
	public void deleteAtividade(Integer id) {
		atividadeRepository.deleteById(id);
	}
	
	private AtividadeDTO converterAtividadeParaDTO(Atividade atividade) {
		
		
		AtividadeDTO atividadeDTO = new AtividadeDTO();
		atividadeDTO.setIdAtividade(atividade.getIdAtividade());
		atividadeDTO.setNomeAtividade(atividade.getNomeAtividade());
		
		List<TurmaDTO> turmaListDTO = new ArrayList<>();
		
		for(Turma turma: atividade.getTurmaList()) {
			
			TurmaDTO turmaDTO = turmaService.converterTurmaParaDTO(turma);
			turmaListDTO.add(turmaDTO);
		}
		
		atividadeDTO.setTurmaList(turmaListDTO);
		
		return atividadeDTO;
	}
	
	
	private Atividade converterDTOParaAtividade(AtividadeDTO atividadeDTO) {
		
		Atividade atividade = new Atividade();
		atividade.setIdAtividade(atividadeDTO.getIdAtividade());
		atividade.setNomeAtividade(atividadeDTO.getNomeAtividade());
		
		return atividade;
	}
	
}
