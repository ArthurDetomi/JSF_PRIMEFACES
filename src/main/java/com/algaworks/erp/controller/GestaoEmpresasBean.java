package com.algaworks.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.erp.model.Empresa;
import com.algaworks.erp.repository.Empresas;

/*
 * @Named para transformar a classe em um Managed Bean
 *  
 * @Request Scoped diz que a isntancia é criada quando a requisição pé iniciada e termina junto com a
 * requisição
 * 
 * @ViewScoped Se inicia quando o usuário carrega a página e ele termina quando a sessão termina ou termina 
 * antes atráves de navegação com algum autocommit dentro de algum componente do JSF, tem um tempo de vida 
 * maior do que scoped, precisa implementar interface serializable
 *  
 * @SessionScoped Se inicia quando a sessão inicia e só termina na proxima sessão
 * 
 * @ApplicationScoped só conseguimos ver ao inicio, pois se inicia junto com a aplicação e só termina ao 
 * fim dela
 */
@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Empresas empresas;

	private List<Empresa> listaEmpresas;

	public void todasEmpresas() {
		listaEmpresas = empresas.todas();
	}

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

}
