package com.teste.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.entities.Cep;
import com.teste.repositories.CepRepository;
import com.teste.services.exceptions.ResourceNotFoundException;

@Service
public class CepService {

	@Autowired
	private CepRepository cepRepository;
	
	public Cep save(Cep object) {
		Cep cep = new Cep(null, object.getFaixa_inicio(), object.getFaixa_fim());
		cep.setLoja(object.getLoja());
		return cepRepository.save(object);
	}
	
	public Cep update(Long id, Cep object) {
		Cep cep = cepRepository.getOne(id);
		updateData(cep, object);
		return cepRepository.save(cep);
	}
	
	public List<Cep> findAll() {
		return cepRepository.findAllActive();
	}
	
	public Cep findById(Long id) {
		Optional<Cep> object = cepRepository.findById(id);
		return object.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Cep findByCep(Long cep) {
		Optional<Cep> object = cepRepository.findByCep(cep);
		return object.orElseThrow(() -> new ResourceNotFoundException(cep));
	}
	
	public Cep findLast() {
		long id = cepRepository.findCepLast();
		Optional<Cep> object = cepRepository.findById(id);
		return object.get();
	}
	
	public void delete(Long id) {
		Cep cep = findById(id);
		cep.setDeleted(1);
		cepRepository.save(cep);
	}
	
	public boolean cepValidateInsert(Cep cep) {
		Cep lastCep = findLast();
		if(cep.getFaixa_inicio() > lastCep.getFaixa_fim() && cep.getFaixa_fim() > cep.getFaixa_inicio()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean cepValidateUpdate(Long id, Cep cep) {
		boolean isValid = false;
		cep.setId(id);
		Cep lastCep = findLast();
		if(cep.getId() == 1) {
			Cep nextCep = findById((long) 2);
			if(cep.getFaixa_fim() < nextCep.getFaixa_inicio()) {
				isValid = true;
			}
		} else if(cep.getId() == lastCep.getId()) {
			Cep prevCep = findById(cep.getId() - 1);
			if(cep.getFaixa_inicio() > prevCep.getFaixa_fim()) {
				isValid = true;
			}
		} else {
			Cep nextCep = findById(cep.getId() + 1);
			Cep prevCep = findById(cep.getId() - 1);
			if(cep.getFaixa_inicio() > prevCep.getFaixa_fim() && cep.getFaixa_fim() < nextCep.getFaixa_inicio()) {
				isValid = true;
			}
		}
		return isValid;
	}
	
	private void updateData(Cep cep, Cep object) {
		cep.setFaixa_inicio(object.getFaixa_inicio());
		cep.setFaixa_fim(object.getFaixa_fim());
		cep.setLoja(object.getLoja());
	}
	
}
