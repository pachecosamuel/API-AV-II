package com.residencia.academia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.residencia.academia.dto.InstrutorDTO;
import com.residencia.academia.entity.Instrutor;
import com.residencia.academia.exception.NoSuchElementFoundException;
import com.residencia.academia.service.InstrutorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/instrutor")
@Tag(name = "Instrutor", description = "Endpoints")
public class InstrutorController {

	@Autowired
	InstrutorService instrutorService;

	@GetMapping
	@Operation(summary = "Lista todos os instrutores")
	public ResponseEntity<List<Instrutor>> findAllInstrutor() {
		List<Instrutor> instrutorList = instrutorService.findAllInstrutor();
		return new ResponseEntity<>(instrutorList, HttpStatus.OK);
	}

	@GetMapping("/dto/{id}")
	@Operation(summary = "Lista um instrutorDTO por ID.")
	public ResponseEntity<InstrutorDTO> findInstrutorDTOById(@PathVariable Integer id) {

		InstrutorDTO instrutorDTO = instrutorService.findInstrutorDTOById(id);

		if (instrutorDTO == null)
			throw new NoSuchElementFoundException("Não foi encontrado o Instrutor com o id: " + id);
		else
			return new ResponseEntity<>(instrutorDTO, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	@Operation(summary = "Lista um instrutor por ID.")
	public ResponseEntity<Instrutor> findInstrutorById(@PathVariable Integer id) {

		Instrutor instrutor = instrutorService.findInstrutorById(id);

		if (null == instrutor)
			throw new NoSuchElementFoundException("Não foi encontrado o instrutor de id:: " + id);
		else
			return new ResponseEntity<>(instrutor, HttpStatus.OK);

	}

	@PostMapping
	@Operation(summary = "Salve um novo instrutor.")
	public ResponseEntity<Instrutor> saveInstrutor(@RequestBody @Valid Instrutor instrutor) {
		Instrutor novoInstrutor = instrutorService.saveInstrutor(instrutor);
		return new ResponseEntity<>(novoInstrutor, HttpStatus.CREATED);
	}

	@PostMapping("/dto")
	@Operation(summary = "Salve um novo instrutorDTO.")
	public ResponseEntity<Instrutor> saveDTO(@RequestBody InstrutorDTO instrutorDTO) {

		return new ResponseEntity<>(instrutorService.saveDTO(instrutorDTO), HttpStatus.CREATED);

	}

	@PostMapping(value = "/com-foto", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	@Operation(summary = "Salver um novo instrutor com foto.")
	public ResponseEntity<Instrutor> saveInstrutorComFoto(@RequestPart("instrutor") String instrutor, @RequestPart("file") MultipartFile file) {
		return new ResponseEntity<>(instrutorService.saveInstrutorComFoto(instrutor, file), HttpStatus.CREATED);
	}

	@PutMapping
	@Operation(summary = "Atualiza um instrutor.")
	public ResponseEntity<Instrutor> updateInstrutor(@RequestBody @Valid Instrutor instrutor) {
		Instrutor novoInstrutor = instrutorService.updateInstrutor(instrutor);
		return new ResponseEntity<>(novoInstrutor, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um instrutor.")
	public ResponseEntity<String> deleteInstrutor(@PathVariable Integer id) {

		Instrutor instrutor = instrutorService.findInstrutorById(id);

		if (null == instrutor)
			throw new NoSuchElementFoundException("Não foi encontrado o instrutor de id:: " + id);

		instrutorService.deleteInstrutor(id);
		return new ResponseEntity<>("Deletado com sucesso.", HttpStatus.OK);
	}
}
