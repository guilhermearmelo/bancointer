package com.inter.apirest.dto;

import com.inter.apirest.models.DigitoUnico;

public class DigitoUnicoDto {
	
	private String n;
	
	private int k;
	
	private int resultado;
	
	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}
	
	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}
	
	public DigitoUnicoDto(DigitoUnico d) {
		this.k=d.getK();
		this.n=d.getN();
		this.resultado=d.getResultado();
	}
}
