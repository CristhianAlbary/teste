package com.teste.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cep")
public class Cep implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false)
	private Long faixa_inicio;
	@Column(unique = true, nullable = false)
	private Long faixa_fim;
	@Column(name = "deleted")
    private Integer deleted = 0;
	
	@ManyToOne
	@JoinColumn(name = "codigo_loja", nullable = false)
	private Loja loja;
	
	public Cep() {
		
	}
	
	public Cep(Long id, Long faixa_inicio, Long faixa_fim) {
		super();
		this.id = id;
		this.faixa_inicio = faixa_inicio;
		this.faixa_fim = faixa_fim;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFaixa_inicio() {
		return faixa_inicio;
	}

	public void setFaixa_inicio(Long faixa_inicio) {
		this.faixa_inicio = faixa_inicio;
	}

	public Long getFaixa_fim() {
		return faixa_fim;
	}

	public void setFaixa_fim(Long faixa_fim) {
		this.faixa_fim = faixa_fim;
	}

	public void setDeleted(int status) {
		this.deleted = status;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cep other = (Cep) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
