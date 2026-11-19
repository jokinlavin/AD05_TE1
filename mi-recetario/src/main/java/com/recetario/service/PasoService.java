package com.recetario.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recetario.model.Paso;
import com.recetario.model.Receta;
import com.recetario.repository.PasoRepository;
import com.recetario.repository.RecetaRepository;

@Service
@Transactional
public class PasoService {

	private final PasoRepository pasoRepository;
	private final RecetaRepository recetaRepository;

	// Inyección de dependencias por constructor
	public PasoService(PasoRepository pasoRepository,
			RecetaRepository recetaRepository) {
		this.pasoRepository = pasoRepository;
		this.recetaRepository = recetaRepository;
	}

	// Obtener todos los pasos
	@Transactional(readOnly = true)
	public List<Paso> listarTodos() {
		return pasoRepository.findAllByOrderByRecetaIdAscNumeroPasoAsc();
	}

	// Buscar un paso por su identificador
	@Transactional(readOnly = true)
	public Paso buscarPorId(Long id) {
		return pasoRepository.findWithRecetaById(id);
	}

	// Obtener los pasos de una receta concreta
	@Transactional(readOnly = true)
	public List<Paso> listarPorReceta(Long recetaId) {
		return pasoRepository.findByRecetaId(recetaId);
	}

	// Guardar un paso
	public Paso guardar(Paso paso) {
		if (paso.getReceta() == null ||
				paso.getReceta().getId() == null) {
			throw new IllegalArgumentException("Debes seleccionar una receta");
		}

		Long recetaId = paso.getReceta().getId();

		Receta receta = recetaRepository.findRecetaById(recetaId);
		if (receta == null) {
			throw new IllegalArgumentException("La receta seleccionada no existe");
		}
		paso.setReceta(receta);

		return pasoRepository.save(paso);
	}

	// Eliminar un paso por su identificador
	public void eliminar(Long id) {
		pasoRepository.deleteById(id);
	}
}
