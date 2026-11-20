package com.recetario.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recetario.model.Receta;
import com.recetario.repository.RecetaRepository;

@Service
@Transactional
public class RecetaService {

	private final RecetaRepository recetaRepository;

	// Dependencias por constructor
	public RecetaService(RecetaRepository recetaRepository) {
		this.recetaRepository = recetaRepository;
	}

	// Obtenemos todas las recetas
	@Transactional(readOnly = true)
	public List<Receta> listarTodas() {
		return recetaRepository.findAll();
	}

	// Buscamos una receta por su identificador
	@Transactional(readOnly = true)
	public Receta buscarPorId(Long id) {
		return recetaRepository.findRecetaById(id);
	}

	// Guardamoss una receta nueva o modificada
	public Receta guardar(Receta receta) {
		return recetaRepository.save(receta);
	}

	// Eliminamos una receta por su identificador
	public void eliminar(Long id) {
		recetaRepository.deleteById(id);
	}
}
