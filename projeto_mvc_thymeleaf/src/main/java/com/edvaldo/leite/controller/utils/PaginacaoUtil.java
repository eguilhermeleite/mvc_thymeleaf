package com.edvaldo.leite.controller.utils;

import java.util.List;

public class PaginacaoUtil<T> {

    // linhas
    private int tamanho;

    private int pagina;

    private Long totalDePaginas;

    private List<T> registros;

    public PaginacaoUtil(int tamanho, int pagina, Long totalDePaginas, List<T> registros) {
	super();
	this.tamanho = tamanho;
	this.pagina = pagina;
	this.totalDePaginas = totalDePaginas;
	this.registros = registros;
    }

    public int getTamanho() {
	return tamanho;
    }

    public int getPagina() {
	return pagina;
    }

    public Long getTotalDePaginas() {
	return totalDePaginas;
    }

    public List<T> getRegistros() {
	return registros;
    }

}
