package com.inter.apirest.dto;

public class EntradaDigitoUnico {
	private String n;
	
	private int k;
	
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
	
	public EntradaDigitoUnico(String n, int k) {
		this.n = n;
		this.k = k;
	}

	public EntradaDigitoUnico() {
	}
	
	@Override
	public boolean equals(Object obj){  
		if (this == obj)   
			return true;  
		if (obj == null || getClass() != obj.getClass())  
			return false;
		EntradaDigitoUnico e = (EntradaDigitoUnico) obj;
		return (this.getN()==e.getN() && this.getK()==e.getK());  
	}
	
	@Override
    public int hashCode() {
        return this.k;
    }
}
