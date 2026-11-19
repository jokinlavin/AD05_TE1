package com.recetario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.recetario.model.Paso;
import com.recetario.service.PasoService;
import com.recetario.service.RecetaService;

import jakarta.validation.Valid;

@Controller
public class PasoController {

	private final PasoService pasoService;
	private final RecetaService recetaService;

	// Inyección de dependencias por constructor
	public PasoController(PasoService pasoService,
			RecetaService recetaService) {
		this.pasoService = pasoService;
		this.recetaService = recetaService;
	}

	// Mostrar todos los pasos
	@GetMapping("/pasos")
	public String listar(Model model) {
		model.addAttribute("pasos", pasoService.listarTodos());
		return "pasos/lista";
	}

	// Mostrar formulario para crear un paso
	@GetMapping("/pasos/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("paso", new Paso());
		cargarRecetas(model);
		return "pasos/formulario";
	}

	// Guardar un paso nuevo o modificado
	@PostMapping("/pasos/guardar")
	public String guardar(
			@Valid @ModelAttribute("paso") Paso paso,
			BindingResult resultado,
			Model model) {

		if (resultado.hasErrors()) {
			cargarRecetas(model);
			return "pasos/formulario";
		}

		if (paso.getReceta() == null ||
				paso.getReceta().getId() == null) {

			resultado.rejectValue(
					"receta",
					"receta.obligatoria",
					"Debes seleccionar una receta");

			cargarRecetas(model);
			return "pasos/formulario";
		}

		pasoService.guardar(paso);

		return "redirect:/pasos";
	}


	// Mostrar formulario para editar un paso
	@GetMapping("/pasos/editar/{id}")
	public String editar(@PathVariable Long id, Model model) {

		Paso paso = pasoService.buscarPorId(id);
		if (paso == null) {
			throw new IllegalArgumentException("Paso no encontrado");
		}

		model.addAttribute("paso", paso);
		cargarRecetas(model);
		return "pasos/formulario";
	}

	// Mostrar confirmación antes de eliminar
	@GetMapping("/pasos/eliminar/{id}")
	public String confirmarEliminar(@PathVariable Long id, Model model) {

		Paso paso = pasoService.buscarPorId(id);
		if (paso == null) {
			throw new IllegalArgumentException("Paso no encontrado");
		}

		model.addAttribute("paso", paso);
		return "pasos/eliminar";
	}

	// Eliminar paso mediante POST
	@PostMapping("/pasos/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		pasoService.eliminar(id);
		return "redirect:/pasos";
	}

	// Cargar recetas disponibles para el selector
	private void cargarRecetas(Model model) {
		model.addAttribute("recetas", recetaService.listarTodas());
	}
}
