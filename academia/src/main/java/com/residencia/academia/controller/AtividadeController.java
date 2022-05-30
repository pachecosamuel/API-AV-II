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

import com.residencia.academia.dto.AtividadeDTO;
import com.residencia.academia.entity.Atividade;
import com.residencia.academia.exception.NoSuchElementFoundException;
import com.residencia.academia.service.AtividadeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/atividade")
@Tag(name = "Atividade", description = "Endpoints")
public class AtividadeController {

	@Autowired
	AtividadeService atividadeService;

	@GetMapping
	@Operation(summary = "Lista todas as atividades")
	public ResponseEntity<List<Atividade>> findAllAtividade() {

		List<Atividade> atividadeList = atividadeService.findAllAtividade();

		return new ResponseEntity<>(atividadeList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Lista uma atividade especificada por id")
	public ResponseEntity<Atividade> findAtividadeById(@PathVariable Integer id) {
		Atividade atividade = atividadeService.findAtividadeById(id);

		if (atividade == null)
			throw new NoSuchElementFoundException("Não foi encontrada a atividade especificada com o id: " + id);
		else
			return new ResponseEntity<>(atividade, HttpStatus.OK);
	}

	@GetMapping("/dto/{id}")
	@Operation(summary = "Lista uma atividadeDTO especificada por id")
	public ResponseEntity<AtividadeDTO> findAtividadeDTOById(@PathVariable Integer id) {
		AtividadeDTO atividadeDTO = atividadeService.findAtividadeDTOById(id);

		if (atividadeDTO == null)
			throw new NoSuchElementFoundException("Não foi encontrado a Atividade com o id: " + id);
		else
			return new ResponseEntity<>(atividadeDTO, HttpStatus.OK);

	}

	@PostMapping
	@Operation(summary = "Salve uma nova atividade.")
	public ResponseEntity<Atividade> saveAtividade(@RequestBody @Valid Atividade atividade) {
		return new ResponseEntity<>(atividadeService.saveAtividade(atividade), HttpStatus.CREATED);
	}

	@PostMapping("/dto")
	@Operation(summary = "Salve uma nova atividadeDTO.")
	public ResponseEntity<Atividade> saveDTO(@RequestBody AtividadeDTO atividadeDTO) {
		return new ResponseEntity<>(atividadeService.saveDTO(atividadeDTO), HttpStatus.CREATED);
	}

	@PutMapping
	@Operation(summary = "Atualiza uma nova atividade.")
	public ResponseEntity<Atividade> updateAtividade(@RequestBody @Valid Atividade atividade) {
		return new ResponseEntity<>(atividadeService.saveAtividade(atividade), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta uma atividade.")
	public ResponseEntity<String> deleteAtividade(@PathVariable Integer id) {

		Atividade atividade = atividadeService.findAtividadeById(id);

		if (atividade == null)
			throw new NoSuchElementFoundException("Não foi possível excluir a atividade de id:  " + id);
		else {
			atividadeService.deleteAtividade(id);
			return new ResponseEntity<>("Deletado com sucesso.", HttpStatus.OK);
		}

	}

}
