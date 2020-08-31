package com.inter.apirest.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inter.apirest.dto.DigitoUnicoDto;
import com.inter.apirest.dto.UsuarioDto;
import com.inter.apirest.models.DigitoUnico;
import com.inter.apirest.models.Usuario;
import com.inter.apirest.repository.DigitoUnicoRepository;
import com.inter.apirest.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private DigitoUnicoRepository digitoUnicoRepository;
	
	public List<UsuarioDto> findAll() {
		
		List<Usuario> lu = new ArrayList<Usuario>();
		List<UsuarioDto> ludto = new ArrayList<UsuarioDto>();
		lu = usuarioRepository.findAll();
		for(Usuario u: lu) {
			UsuarioDto dto = new UsuarioDto(u);
			ludto.add(dto);
		}
		
		return ludto;
	}
	
	public UsuarioDto findById(long id) {
		
		Usuario usuario = usuarioRepository.findById(id);
		UsuarioDto udto = null;
		if(usuario!=null) {
			udto = new UsuarioDto(usuario);
			List<DigitoUnico> listaDigitoUnico = digitoUnicoRepository.findByUsuario(usuario);
			for(DigitoUnico d : listaDigitoUnico) {
				DigitoUnicoDto ddto = new DigitoUnicoDto(d);
				udto.setDigitoUnico(ddto);
			}
		}
		
		return udto;
	}
	
	public Usuario insert(Usuario usuario) {
		
		if(usuario.getDigitosUnicos() == null) {
			usuario.setDigitosUnicos(Collections.emptyList());
		}
		
		return usuarioRepository.save(usuario);
	}
	
	public Usuario insert(Usuario usuario, DigitoUnico du) {
		usuario.setDigitoUnico(du);
		return usuarioRepository.save(usuario);
	}
	
	public void remove(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}
	
	public void removeById(long id) {
		Usuario usuario = usuarioRepository.findById(id);
		usuarioRepository.delete(usuario);
	}
	
	public Usuario update(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
//	public Usuario parseDtoToEntity(UsuarioDto dto) {
//		Usuario entity = new Usuario();
//		
//		entity.setNome(dto.getNome());
//		entity.setEmail(dto.getEmail());
//		entity.
//	}
}
