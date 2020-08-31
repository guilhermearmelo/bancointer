package com.inter.apirest.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.inter.apirest.dto.EntradaDigitoUnico;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DigitoUnicoServiceTest {

	@Autowired
	DigitoUnicoService dus;
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	public WebApplicationContext context;
	
	// Testes Unitários para verificar se a entrada pode ser inválida em 'n' 
	
	@Test
	public void testaEntradasDigitoUnico() {
		EntradaDigitoUnico entrada = new EntradaDigitoUnico("123", 1);
		assertThat(dus.calcularDigitoUnico(entrada)).isInstanceOf(ResponseEntity.class);
		entrada = new EntradaDigitoUnico("123", 2);
		assertThat(dus.calcularDigitoUnico(entrada)).isInstanceOf(ResponseEntity.class);
		entrada = new EntradaDigitoUnico("123", 3);
		assertThat(dus.calcularDigitoUnico(entrada)).isInstanceOf(ResponseEntity.class);
		entrada = new EntradaDigitoUnico("1", 20);
		assertThat(dus.calcularDigitoUnico(entrada)).isInstanceOf(ResponseEntity.class);
		entrada = new EntradaDigitoUnico("121345354453425134", 67);
		assertThat(dus.calcularDigitoUnico(entrada)).isInstanceOf(ResponseEntity.class);
	}
	
	@Test
	public void testaEntradasNaoNumerosDigitoUnico() {
		EntradaDigitoUnico entrada = new EntradaDigitoUnico("123", 1);
		entrada = new EntradaDigitoUnico("aaaa", 20);
		assertThat(dus.calcularDigitoUnico(entrada)).isInstanceOf(ResponseEntity.class);
	}
	
	// Teste do retorno do função de geração do dígito único
	@Test
	public void testeDigitoUnico() {
		assertThat(digitoUnico("123", 1)).isEqualTo(6);
		assertThat(digitoUnico("123", 2)).isEqualTo(3);
		assertThat(digitoUnico("123", 3)).isEqualTo(9);
		assertThat(digitoUnico("1", 20)).isEqualTo(2);
		assertThat(digitoUnico("121345354453425134", 67)).isEqualTo(2);
		
	}
	
	private int digitoUnico(String n, int k) {
		
		if(n.length() == 1 && k==1) return Integer.parseInt(n);
		
		int vetor[] = new int[10];
		int num=0;
		
		for(int i=0; i<10; i++) {
			vetor[i]=0;
		}
		
		for(int i = 0; i < n.length() ; i++) {
		    int x = Integer.parseInt((new Character(n.charAt(i))).toString());
		    vetor[x]++;
		}
		
		for(int i=0; i<10; i++) {
			num += (i*vetor[i]*k);
		}
		
		return digitoUnico(Integer.toString(num), 1);
	}
	
}
