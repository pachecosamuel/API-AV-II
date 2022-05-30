package com.residencia.academia.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.residencia.academia.dto.InstrutorDTO;
import com.residencia.academia.dto.TurmaDTO;
import com.residencia.academia.entity.Instrutor;
import com.residencia.academia.entity.Turma;
import com.residencia.academia.repository.InstrutorRepository;

@Service
public class InstrutorService {

	@Value("${files.folder.path}")
	private Path path;
	
	@Autowired
	InstrutorRepository instrutorRepository;

//	@Autowired
//	private TurmaService turmaService;

	public List<Instrutor> findAllInstrutor() {
		return instrutorRepository.findAll();
	}

	public Instrutor findInstrutorById(Integer id) {
		return instrutorRepository.findById(id).isPresent() ? instrutorRepository.findById(id).get() : null;
	}

	public InstrutorDTO findInstrutorDTOById(Integer id) {
		return instrutorRepository.findById(id).isPresent()
				? converterInstrutorParaDTO(instrutorRepository.findById(id).get())
				: null;
	}

	public Instrutor saveInstrutor(Instrutor instrutor) {
		return instrutorRepository.save(instrutor);
	}

	public Instrutor saveDTO(InstrutorDTO instrutorDTO) {
		Instrutor instrutor = converterDTOParaInstrutor(instrutorDTO);
		return instrutorRepository.save(instrutor);
	}

	public Instrutor updateInstrutor(Instrutor instrutor) {
		return instrutorRepository.save(instrutor);
	}

	public void deleteInstrutor(Integer id) {
		Instrutor inst = instrutorRepository.findById(id).get();
		instrutorRepository.delete(inst);
	}

	public InstrutorDTO converterInstrutorParaDTO(Instrutor instrutor) {

		InstrutorDTO instrutorDTO = new InstrutorDTO();

		instrutorDTO.setDataNascimento(instrutor.getDataNascimento());
		instrutorDTO.setIdInstrutor(instrutor.getIdInstrutor());
		instrutorDTO.setNomeInstrutor(instrutor.getNomeInstrutor());
		instrutorDTO.setRg(instrutor.getRg());
		instrutorDTO.setTitulacaoInstrutor(instrutor.getTitulacaoInstrutor());

		List<TurmaDTO> listTurmaDTO = new ArrayList<>();

		for (Turma turma : instrutor.getTurmaList()) {
			TurmaDTO turmaDTO = new TurmaDTO();

			turmaDTO.setDataFim(turma.getDataFim());
			turmaDTO.setDataInicio(turma.getDataInicio());
			turmaDTO.setDuracaoTurma(turma.getDuracaoTurma());
			turmaDTO.setHorarioTurma(turma.getHorarioTurma());
			turmaDTO.setIdTurma(turma.getIdTurma());

			listTurmaDTO.add(turmaDTO);
		}

		instrutorDTO.setTurmaDTOList(listTurmaDTO);

		return instrutorDTO;
	}

	public Instrutor converterDTOParaInstrutor(InstrutorDTO instrutorDTO) {

		Instrutor instrutor = new Instrutor();

		instrutor.setDataNascimento(instrutorDTO.getDataNascimento());
		instrutor.setIdInstrutor(instrutorDTO.getIdInstrutor());
		instrutor.setNomeInstrutor(instrutorDTO.getNomeInstrutor());
		instrutor.setRg(instrutorDTO.getRg());
		instrutor.setTitulacaoInstrutor(instrutorDTO.getTitulacaoInstrutor());

		return instrutor;
	}

	public Instrutor saveInstrutorComFoto(String instrutor, MultipartFile file) {
		Instrutor newInstrutor = new Instrutor();

		try {
			ObjectMapper objMapper = new ObjectMapper();
			newInstrutor = objMapper.readValue(instrutor, Instrutor.class);
		} catch (IOException e) {
			System.out.println("Erro de conversão");
			e.printStackTrace();
		}

		Instrutor instrutorSaved = instrutorRepository.save(newInstrutor);

		String filename = "instrutor." + instrutorSaved.getIdInstrutor() + ".image.png";

		try {
			Files.copy(file.getInputStream(), path.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			instrutorSaved.setFotoInstrutor(path.resolve(filename).toRealPath().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return instrutorRepository.save(instrutorSaved);
	}

	/*
	 * MODELO QUE ESTAVA DANDO CERTO private InstrutorDTO
	 * converterEntidadeParaDto(Instrutor instrutor) {
	 * 
	 * InstrutorDTO instrutorDTO = new InstrutorDTO();
	 * 
	 * instrutorDTO.setDataNascimento(instrutor.getDataNascimento());
	 * instrutorDTO.setIdInstrutor(instrutor.getIdInstrutor());
	 * instrutorDTO.setNomeInstrutor(instrutor.getNomeInstrutor());
	 * instrutorDTO.setRg(instrutor.getRg());
	 * instrutorDTO.setTitulacaoInstrutor(instrutor.getTitulacaoInstrutor());
	 * 
	 * List<TurmaDTO> listTurmaDTO = new ArrayList<>();
	 * 
	 * for (Turma turma : instrutor.getTurmaList()) {
	 * 
	 * TurmaDTO turmaDTO = turmaService.converterTurmaParaDTO(turma);
	 * 
	 * listTurmaDTO.add(turmaDTO); }
	 * 
	 * instrutorDTO.setTurmaDTOList(listTurmaDTO);
	 * 
	 * return instrutorDTO; }
	 */

}
