package com.algaworks.erp.utils;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;

@Priority(Interceptor.Priority.APPLICATION)// para não precisar configurar o beans.xml
@Interceptor // diz que é um interceptador
@Transacional // marcando o interceptor para dizer ao CDI para interceptar todos os métodos desta classe
public class TransactionalInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject // para ser injetado pelo CDI
	private EntityManager manager;

	/****
	 metodo interceptador, todo metodo anotado com @Transacional o CDI vai interceptar e passando seu contexto
	 de invocação
	 ****/
	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		EntityTransaction trx = manager.getTransaction();
		boolean criador = false;

		try {
			if (!trx.isActive()) {
				// truque para fazer rollback no que já passou
				// (senão, um futuro commit confirmaria até mesmo operações sem
				// transação)
				trx.begin();
				trx.rollback();

				// agora sim inicia a transação
				trx.begin();

				criador = true;
			}

			return context.proceed();
		} catch (Exception e) {
			if (trx != null && criador) {
				trx.rollback();
			}

			throw e;
		} finally {
			if (trx != null && trx.isActive() && criador) {
				trx.commit();
			}
		}
	}

}
