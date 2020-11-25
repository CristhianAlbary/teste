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

import com.teste.entities.Loja;
import com.teste.services.LojaService;

@RestController
@RequestMapping(value = "/lojas")
public class LojaResource {

	@Autowired
	private LojaService lojaService;
	
	@PostMapping
	public ResponseEntity<Loja> save(@RequestBody Loja object) {
		Loja loja = lojaService.save(object);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(object.getId()).toUri();
		return ResponseEntity.created(uri).body(loja);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Loja> update(@PathVariable Long id, @RequestBody Loja object) {
		object = lojaService.update(id, object);
		return ResponseEntity.ok().body(object);
	}
	
	@GetMapping
	public ResponseEntity<List<Loja>> findAll() {
		List<Loja> lojas = lojaService.findAll();
		return ResponseEntity.ok().body(lojas);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Loja> findById(@PathVariable Long id) {
		Loja object = lojaService.findById(id);
		return ResponseEntity.ok().body(object);
	}
	
	@GetMapping(value = "/findbycep/{cep}")
	public ResponseEntity<Loja> findByCep(@PathVariable Long cep) {
		Loja object = lojaService.findByCep(cep);
		return ResponseEntity.ok().body(object);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		lojaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}