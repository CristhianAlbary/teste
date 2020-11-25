package com.teste.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teste.entities.Cep;

public interface CepRepository extends JpaRepository<Cep, Long> {
	@Query(value = "select id from cep where faixa_fim = (select max(faixa_fim) from cep)", nativeQuery = true)
	long findCepLast();
	
	@Query(value = "select * from cep where deleted = 0", nativeQuery = true)
	List<Cep> findAllActive();
	
	@Query(value = "select * from cep where cep.faixa_inicio <= ?1 and cep.faixa_fim >= ?1", nativeQuery = true)
	Optional<Cep> findByCep(Long cep);
}