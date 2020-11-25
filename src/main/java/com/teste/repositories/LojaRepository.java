package com.teste.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teste.entities.Loja;

public interface LojaRepository extends JpaRepository<Loja, Long> {
	@Query(value = "select loja.id, loja.nome from loja join cep on cep.codigo_loja = loja.id where cep.faixa_inicio <= ?1 and cep.faixa_fim >= ?1", nativeQuery = true)
	Optional<Loja> findByCep(Long cep);
}