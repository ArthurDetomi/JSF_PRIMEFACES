package com.algaworks.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.algaworks.erp.model.Empresa;

public class Empresas implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	public Empresas() {

	}

	public Empresas(EntityManager manager) {
		this.manager = manager;
	}

	public Empresa porId(Long id) {
		return manager.find(Empresa.class, id);
	}

	public List<Empresa> pesquisar(String nome) {
		String jpql = "from Empresa where nomeFantasia like :nomeFantasia";
		
		Query query = manager.createQuery(jpql, Empresa.class);
		query.setParameter("nomeFantasia", nome + "%");

		return query.getResultList();
	}

	public Empresa guardar(Empresa empresa) {
		// se passa uma empresa que já existe ele atualizam, se não ele salva
		return manager.merge(empresa);
	}

	public void remover(Empresa empresa) {
		// só entity manager só remove objetos que está em seu contexto ou seja que ele
		// está gerenciando
		empresa = porId(empresa.getId());
		manager.remove(empresa);
	}

}
