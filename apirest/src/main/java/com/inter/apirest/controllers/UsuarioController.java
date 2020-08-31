package com.inter.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inter.apirest.dto.DigitoUnicoDto;
import com.inter.apirest.dto.UsuarioDto;
import com.inter.apirest.dto.EntradaDigitoUnico;
import com.inter.apirest.models.DigitoUnico;
import com.inter.apirest.models.Usuario;
import com.inter.apirest.repository.DigitoUnicoRepository;
import com.inter.apirest.repository.UsuarioRepository;
import com.inter.apirest.service.DigitoUnicoService;
import com.inter.apirest.service.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="API REST Usuarios")
@CrossOrigin(origins="*")
public class UsuarioController {
	
//	@Autowired
//	UsuarioRepository usuarioRepository;
//	
//	@Autowired
//	DigitoUnicoRepository digitoUnicoRepository;

	@Autowired
	DigitoUnicoService dus;
	
	@Autowired
	UsuarioService us;
	
	// endpoints para o CRUD de usuários
	
	@GetMapping("/usuarios")
	@ApiOperation(value = "Retorna lista de usuarios")
	public List<UsuarioDto> listaUsuarios(){
		return us.findAll();
	}
	
	@GetMapping("/usuario/{id}")
	@ApiOperation(value = "Retorna usuario pelo id")
	public UsuarioDto listaUsuarioUnico(@PathVariable(value="id") long id){
		return us.findById(id);
	}
	
	@PostMapping("/usuario")
	@ApiOperation(value = "Insere usuario")
	public Usuario salvaUsuario(@RequestBody Usuario usuario) {
		return us.insert(usuario);
	}
	
	@DeleteMapping("/usuario")
	@ApiOperation(value = "Remove usuario")
	public void deleteUsuario(@RequestBody Usuario usuario) {
		us.remove(usuario);
	}
	
	@DeleteMapping("/usuario/{id}")
	@ApiOperation(value = "Remove usuario pelo id")
	public void deleteUsuarioById(@PathVariable(value="id") long id) {
		us.removeById(id);
	}
	
	@PutMapping("/usuario")
	@ApiOperation(value = "Atualiza usuario")
	public Usuario atualizaUsuario(@RequestBody Usuario usuario) {
		return us.update(usuario);
	}
	
	// endpoint para cálculo do dígito único
	@GetMapping("/calcular")
	@ApiOperation(value = "Endpoint para cálculo do dígito único")
	public ResponseEntity<Integer> calcularDigitoUnico(@RequestBody EntradaDigitoUnico pdu) {
		return dus.calcularDigitoUnico(pdu);
	}
	
	// endpoint para cálculo do dígito único por usuário
	@GetMapping("/calcular/{id}")
	@ApiOperation(value = "Endpoint para cálculo do dígito único por usuário")
	public ResponseEntity<Integer> calcularDigitoUnicoById(@RequestBody EntradaDigitoUnico pdu, @PathVariable(value="id") long id) {
		return dus.calcularDigitoUnico(pdu, id);
	}
	
	// endpoint para retornar dígitos únicos já calculados por um usuário
	@GetMapping("/info/{id}")
	@ApiOperation(value = "Endpoint para retornar dígitos únicos já calculados por um usuário")
	public ResponseEntity<UsuarioDto> informacoesUsuario(@PathVariable(value="id") long id) {
		return dus.retornarInformacoes(id);
	}
	
}