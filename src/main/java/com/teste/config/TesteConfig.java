package com.teste.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.teste.entities.Cep;
import com.teste.entities.Loja;
import com.teste.repositories.CepRepository;
import com.teste.repositories.LojaRepository;

@Configuration
public class TesteConfig implements CommandLineRunner {

	@Autowired
	private LojaRepository lojaRepository;
	@Autowired
	private CepRepository cepRepository;

	@Override
	public void run(String... args) throws Exception {
		Loja loja1 = new Loja(null, "LOJA_PINHEIROS");
		Loja loja2 = new Loja(null, "LOJA_JARDINS");
		
		lojaRepository.saveAll(Arrays.asList(loja1, loja2));
		
		Cep cep1 = new Cep(null, (long) 10000000, (long) 20000000);
		cep1.setLoja(loja1);
		Cep cep2 = new Cep(null, (long) 20000001, (long) 30000000);
		cep2.setLoja(loja1);
		Cep cep3 = new Cep(null, (long) 30000001, (long) 40000000);
		cep3.setLoja(loja2);
		Cep cep4 = new Cep(null, (long) 40000001, (long) 50000000);
		cep4.setLoja(loja2);
		
		cepRepository.saveAll(Arrays.asList(cep1, cep2, cep3, cep4));
	}
	
	
	
}
