package com.inter.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inter.apirest.models.DigitoUnico;
import com.inter.apirest.models.Usuario;

@Repository
public interface DigitoUnicoRepository extends JpaRepository<DigitoUnico, Long>{
	DigitoUnico findById(long id);
	List<DigitoUnico> findByNAndK(String n, int k);
	List<DigitoUnico> findByUsuario(Usuario usuario);
	
}