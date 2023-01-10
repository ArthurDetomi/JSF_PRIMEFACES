package com.algaworks.id;

public class Main {
    // Pequeno exemplo de injeção de dependências
    public static void main(String[] args) {
        Pedidos pedidos = new Pedidos();
        RelatorioService relatorioService = new RelatorioService();
        relatorioService.setPedidos(pedidos);
    }



}
