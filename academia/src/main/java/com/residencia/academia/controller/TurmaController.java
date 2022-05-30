package com.residencia.academia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.academia.dto.TurmaDTO;
import com.residencia.academia.entity.Turma;
import com.residencia.academia.exception.NoSuchElementFoundException;
import com.residencia.academia.service.TurmaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/turma")
@Tag(name = "Turma", description = "Endpoints")
public class TurmaController {

	@Autowired
	private TurmaService turmaService;

	@GetMapping
	@Operation(summary = "Lista todas as turmas")
	public ResponseEntity<List<Turma>> findAllTurma() {
		return new ResponseEntity<>(turmaService.findAllTurma(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Lista uma turma especificada por ID.")
	public ResponseEntity<Turma> findTurmaById(@PathVariable Integer id) {

		Turma turma = turmaService.findTurmaById(id);
		if (null == turma)
			throw new NoSuchElementFoundException("Não foi encontrada Turma com o id: " + id);
		else
			return new ResponseEntity<>(turma, HttpStatus.OK);
	}

	@GetMapping("/dto/{id}")
	@Operation(summary = "Lista uma turmaDTO especificada por ID.")
	public ResponseEntity<TurmaDTO> findTurmaDTOById(@PathVariable Integer id) {
		TurmaDTO turmaDTO = turmaService.findTurmaDTOById(id);

		if (turmaDTO == null)
			throw new NoSuchElementFoundException("Não foi encontrado a Turma com o id " + id);
		else
			return new ResponseEntity<>(turmaDTO, HttpStatus.OK);

	}

	@PostMapping
	@Operation(summary = "Salve uma nova turma.")
	public ResponseEntity<Turma> saveTurma(@RequestBody @Valid Turma turma) {
		return new ResponseEntity<>(turmaService.saveTurma(turma), HttpStatus.CREATED);
	}

	@PostMapping("/dto")
	@Operation(summary = "Salve uma nova turmaDTO.")
	public ResponseEntity<Turma> saveDTO(@RequestBody TurmaDTO turmaDTO) {
		return new ResponseEntity<>(turmaService.saveDTO(turmaDTO), HttpStatus.OK);
	}

	@PutMapping
	@Operation(summary = "Atualiza uma turma.")
	public ResponseEntity<Turma> updateTurma(@RequestBody @Valid Turma turma) {
		return new ResponseEntity<>(turmaService.updateTurma(turma), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta uma turma.")
	public ResponseEntity<String> deleteTurma(@PathVariable Integer id) {

		Turma turma = turmaService.findTurmaById(id);
		if (null == turma)
			throw new NoSuchElementFoundException("Não foi possível excluir a turma de id:  " + id);

		turmaService.deleteTurma(id);
		return new ResponseEntity<>("Deletado com sucesso.", HttpStatus.OK);
	}

}
