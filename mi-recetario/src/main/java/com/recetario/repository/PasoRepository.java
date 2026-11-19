package com.recetario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recetario.model.Paso;

@Repository
public interface PasoRepository extends JpaRepository<Paso, Long> {

	List<Paso> findByRecetaId(Long recetaId);

	@EntityGraph(attributePaths = "receta")
	List<Paso> findAllWithRecetaBy();

	@EntityGraph(attributePaths = "receta")
	Paso findWithRecetaById(Long id);

	List<Paso> findAllByOrderByRecetaIdAscNumeroPasoAsc();
}
