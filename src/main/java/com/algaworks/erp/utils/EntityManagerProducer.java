package com.algaworks.erp.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped // diz que esta instância deve sobreviver a todo ciclo de vida da aplicação
public class EntityManagerProducer {

	private EntityManagerFactory factory;

	public EntityManagerProducer() {
		this.factory = Persistence.createEntityManagerFactory("AlgaWorksPU");
	}

	@Produces // diz que esse metodo é um metodo produtor de entity manager
	@RequestScoped // a cada requisição será uma instacia nova
	public EntityManager createEntityManager() {
		return this.factory.createEntityManager();
	}

	public void closeEntityManager(@Disposes EntityManager manager) {
		manager.close();
	}

}
