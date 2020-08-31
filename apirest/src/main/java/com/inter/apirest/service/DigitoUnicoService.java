package com.inter.apirest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.inter.apirest.cache.Cache;
import com.inter.apirest.dto.DigitoUnicoDto;
import com.inter.apirest.dto.EntradaDigitoUnico;
import com.inter.apirest.dto.UsuarioDto;
import com.inter.apirest.models.DigitoUnico;
import com.inter.apirest.models.Usuario;
import com.inter.apirest.repository.DigitoUnicoRepository;
import com.inter.apirest.repository.UsuarioRepository;

@Service
public class DigitoUnicoService {
	@Autowired
	private DigitoUnicoRepository digitoUnicoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	public ResponseEntity calcularDigitoUnico(EntradaDigitoUnico pdu) {
		String regex = "[0-9]+";
		Pattern p = Pattern.compile(regex);
		if(pdu.getN()==null || pdu.getN()=="" || !p.matcher(pdu.getN()).matches()) {
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
		// Calculo de digito sem usuario envolvido
		
		Cache cache = new Cache();
		int resultado=0;
		if(cache.search(pdu) == -1) {
			List<DigitoUnico> ldu_banco = digitoUnicoRepository.findByNAndK(pdu.getN(), pdu.getK());
			
			
			if(ldu_banco.isEmpty()) {
				resultado = digitoUnico(pdu.getN(), pdu.getK()); 
			}else {
				resultado = ldu_banco.get(0).getResultado();
			}
			
			DigitoUnico du = new DigitoUnico();
			du.setN(pdu.getN());
			du.setK(pdu.getK());
			du.setResultado(resultado);
			digitoUnicoRepository.save(du);
			
			DigitoUnicoDto ddto = new DigitoUnicoDto(du);
			cache.insert(ddto);
		}else {
			resultado = cache.search(pdu);
			
			DigitoUnico du = new DigitoUnico();
			du.setN(pdu.getN());
			du.setK(pdu.getK());
			du.setResultado(resultado);
			digitoUnicoRepository.save(du);
			
			DigitoUnicoDto ddto = new DigitoUnicoDto(du);
			cache.insert(ddto);
		}
		
		return new ResponseEntity<Integer>(resultado, HttpStatus.OK);
	
	}
	
	public ResponseEntity calcularDigitoUnico(EntradaDigitoUnico pdu, long id) {
		
		String regex = "[0-9]+";
		Pattern p = Pattern.compile(regex);
		if(id < 0 || pdu.getN()==null || pdu.getN()=="" || !p.matcher(pdu.getN()).matches()) {
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
		
		Usuario usuario = usuarioRepository.findById(id);
		// Calculo de digito com usuario envolvido
		
		Cache cache = new Cache();
		int resultado=0;
		if(cache.search(pdu) == -1) {
			List<DigitoUnico> ldu_banco = digitoUnicoRepository.findByNAndK(pdu.getN(), pdu.getK());
			
			if(ldu_banco.isEmpty()) {
				resultado = digitoUnico(pdu.getN(), pdu.getK()); 
			}else {
				resultado = ldu_banco.get(0).getResultado();
			}
			
			DigitoUnico du = new DigitoUnico();
			du.setN(pdu.getN());
			du.setK(pdu.getK());
			du.setResultado(resultado);
			du.setUsuario(usuario);
			digitoUnicoRepository.save(du);
			
			DigitoUnicoDto ddto = new DigitoUnicoDto(du);
			cache.insert(ddto);
		}else {
			resultado = cache.search(pdu);
			
			DigitoUnico du = new DigitoUnico();
			du.setN(pdu.getN());
			du.setK(pdu.getK());
			du.setResultado(resultado);
			du.setUsuario(usuario);
			digitoUnicoRepository.save(du);
			
			DigitoUnicoDto ddto = new DigitoUnicoDto(du);
			cache.insert(ddto);
		}
		
		return new ResponseEntity<Integer>(resultado, HttpStatus.OK);
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
	
	public ResponseEntity<UsuarioDto> retornarInformacoes(long id) {
		if(id < 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Usuario usuario = new Usuario();
		
		usuario = usuarioRepository.findById(id);
		
		if(usuario == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		UsuarioDto udto = new UsuarioDto(usuario);
		
		List<DigitoUnico> ldu = new ArrayList<DigitoUnico>();
		ldu.addAll(digitoUnicoRepository.findByUsuario(usuario));
		
		for(DigitoUnico du : ldu) {
			DigitoUnicoDto ddto = new DigitoUnicoDto(du);
			udto.setDigitoUnico(ddto);
		}
		
		return new ResponseEntity<UsuarioDto>(udto,HttpStatus.OK);
	}
}
