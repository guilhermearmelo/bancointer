package com.inter.apirest.dto;

import java.util.ArrayList;
import java.util.List;


import com.inter.apirest.models.DigitoUnico;
import com.inter.apirest.models.Usuario;

public class UsuarioDto {
	private String nome;
	
	private String email;
	
	private List<DigitoUnicoDto> digitosUnicos;

	public List<DigitoUnicoDto> getDigitosUnicos() {
		return digitosUnicos;
	}

	public void setDigitosUnicos(List<DigitoUnicoDto> digitosUnicos) {
		this.digitosUnicos = digitosUnicos;
	}
	
	public void setDigitoUnico(DigitoUnicoDto du) {
		this.digitosUnicos.add(du);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public UsuarioDto(Usuario usuario) {
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.digitosUnicos = new ArrayList<>();
	}
}
