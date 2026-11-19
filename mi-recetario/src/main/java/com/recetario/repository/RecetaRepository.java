package com.recetario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recetario.model.Receta;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {
	Receta findRecetaById(Long id);

}
