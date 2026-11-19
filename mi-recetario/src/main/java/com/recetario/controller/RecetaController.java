package com.recetario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.recetario.model.Receta;
import com.recetario.service.RecetaService;

import jakarta.validation.Valid;

@Controller
public class RecetaController {

	private final RecetaService recetaService;

	// Inyección de dependencias por constructor
	public RecetaController(RecetaService recetaService) {
		this.recetaService = recetaService;
	}

	// Mostrar todas las recetas
	@GetMapping("/recetas")
	public String listar(Model model) {
		model.addAttribute("recetas", recetaService.listarTodas());
		return "recetas/lista";
	}

	// Mostrar formulario para crear una receta
	@GetMapping("/recetas/nueva")
	public String nueva(Model model) {
		model.addAttribute("receta", new Receta());
		return "recetas/formulario";
	}

	// Guardar una receta nueva o modificada
	@PostMapping("/recetas/guardar")
	public String guardar(
			@Valid @ModelAttribute("receta") Receta receta,
			BindingResult resultado) {

		if (resultado.hasErrors()) {
			return "recetas/formulario";
		}

		if (receta.getId() != null) {

			Receta existente = recetaService.buscarPorId(receta.getId());
			if (existente == null) {
				new IllegalArgumentException("Receta no encontrada");
			}

			existente.setNombre(receta.getNombre());
			existente.setDescripcion(receta.getDescripcion());
			existente.setTipoCocina(receta.getTipoCocina());
			existente.setTiempoPreparacion(receta.getTiempoPreparacion());

			recetaService.guardar(existente);

		} else {
			recetaService.guardar(receta);
		}

		return "redirect:/recetas";
	}



	// Mostrar formulario para editar una receta
	@GetMapping("/recetas/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Receta receta = recetaService.buscarPorId(id);
        if(receta==null) {       
                     new IllegalArgumentException("Receta no encontrada");
                  }

        model.addAttribute("receta", receta);
        return "recetas/formulario";
    }

	// Mostrar confirmación antes de eliminar
	@GetMapping("/recetas/eliminar/{id}")
	public String confirmarEliminar(@PathVariable Long id, Model model) {

		Receta receta = recetaService.buscarPorId(id);
		if (receta == null) {
			new IllegalArgumentException("Receta no encontrada");
		}
		model.addAttribute("receta", receta);
		return "recetas/eliminar";
	}

	// Eliminar receta mediante POST
	@PostMapping("/recetas/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		recetaService.eliminar(id);
		return "redirect:/recetas";
	}
}
