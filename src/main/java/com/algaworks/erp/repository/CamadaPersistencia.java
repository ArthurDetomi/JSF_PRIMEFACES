package com.algaworks.erp.repository;

import com.algaworks.erp.model.Empresa;
import com.algaworks.erp.model.RamoAtividade;
import com.algaworks.erp.model.TipoEmpresa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class CamadaPersistencia {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AlgaWorksPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        //declarando os repositorios
        RamoAtividades ramoAtividades = new RamoAtividades(em);
        Empresas empresas = new Empresas(em);

        //buscando as informações no banco
        List<RamoAtividade> ramoAtividadeList = ramoAtividades.pesquisar("");
        List<Empresa> empresaList = empresas.pesquisar("");
        System.out.println(empresaList);


        RamoAtividade ramoAtividade = new RamoAtividade();
        ramoAtividade.setDescricao("Comercio");
        ramoAtividade.setId(10L);

        //criando uma empresa
        Empresa empresa = new Empresa();
        empresa.setNomeFantasia("Joao da Silva");
        empresa.setDataFundacao(new Date());
        empresa.setCnpj("08.939.156/0001-44");
        empresa.setTipo(TipoEmpresa.LTDA);
        empresa.setRamoAtividade(ramoAtividade);
        empresa.setRazaoSocial("Mamonas");
        empresa.setId(10L);


        empresas.guardar(empresa);

        em.getTransaction().commit();

        List<Empresa> empresaList1 = empresas.pesquisar("");
        System.out.println(empresaList1);


    }


}
