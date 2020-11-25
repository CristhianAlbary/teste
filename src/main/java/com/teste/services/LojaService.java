package com.teste.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.entities.Loja;
import com.teste.repositories.LojaRepository;
import com.teste.services.exceptions.ResourceNotFoundException;

@Service
public class LojaService {

	@Autowired
	private LojaRepository lojaRepository;
	
	public Loja save(Loja object) {
		return lojaRepository.save(object);
	}
	
	public List<Loja> findAll() {
		return lojaRepository.findAll();
	}
	
	public Loja findById(Long id) {
		Optional<Loja> object = lojaRepository.findById(id);
		return object.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Loja findByCep(Long cep) {
		Optional<Loja> object = lojaRepository.findByCep(cep);
		return object.orElseThrow(() -> new ResourceNotFoundException(cep));
	}
	
	public Loja update(Long id, Loja object) {
		Loja loja = lojaRepository.getOne(id);
		updateData(loja, object);
		return lojaRepository.save(loja);
	}
	
	public void delete(Long id) {
		lojaRepository.deleteById(id);
	}
	
	private void updateData(Loja loja, Loja object) {
		loja.setNome(object.getNome());
	}
	
}
