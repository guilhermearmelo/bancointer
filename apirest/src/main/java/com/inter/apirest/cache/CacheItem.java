package com.inter.apirest.cache;

public class CacheItem {
	int resultado;
	
	int idadeItem;

	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}

	public int getIdadeItem() {
		return idadeItem;
	}

	public void setIdadeItem(int idadeItem) {
		this.idadeItem = idadeItem;
	}

	public CacheItem(int r, int i) {
		this.resultado = r;
		this.idadeItem = i;
	}

	public CacheItem() {
	}
	
}