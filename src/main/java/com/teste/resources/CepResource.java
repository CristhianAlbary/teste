package com.teste.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.teste.entities.Cep;
import com.teste.resources.exceptions.ResourceExceptionHandler;
import com.teste.services.CepService;

@RestController
@RequestMapping(value = "/cep")
public class CepResource {

	@Autowired
	private CepService cepService;

	@PostMapping
	public ResponseEntity<Object> save(@RequestBody Cep object) {
		if (cepService.cepValidateInsert(object)) {
			object.setDeleted(0);
			Cep cep = cepService.save(object);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(object.getId())
					.toUri();
			return ResponseEntity.created(uri).body(cep);
		}
		return ResourceExceptionHandler.createErrorException("Não foi possivel inserir o registro");
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Cep object) {
		if (cepService.cepValidateUpdate(id, object)) {
			object.setDeleted(0);
			object = cepService.update(id, object);
			return ResponseEntity.ok().body(object);
		}
		return ResourceExceptionHandler.createErrorException("Faixa de cep incorreta.");
	}

	@GetMapping
	public ResponseEntity<List<Cep>> findAll() {
		List<Cep> cepList = cepService.findAll();
		return ResponseEntity.ok().body(cepList);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Cep> findById(@PathVariable Long id) {
		Cep object = cepService.findById(id);
		return ResponseEntity.ok().body(object);
	}

	@GetMapping(value = "/findbycep/{cep}")
	public ResponseEntity<Cep> findByCep(@PathVariable Long cep) {
		Cep object = cepService.findByCep(cep);
		return ResponseEntity.ok().body(object);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		cepService.delete(id);
		return ResponseEntity.noContent().build();
	}

}