package com.recetario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "PASO")
public class Paso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El número de paso es obligatorio")
    @Min(value = 1, message = "El número de paso debe ser mayor que cero")
    @Column(nullable = false)
    private Integer numeroPaso;

    @NotBlank(message = "Las instrucciones son obligatorias")
    @Size(max = 1000)
    @Column(nullable = false, length = 1000)
    private String instrucciones;

    @NotNull(message = "La duración es obligatoria")
    @Min(value = 0, message = "La duración no puede ser negativa")
    @Column(nullable = false)
    private Integer duracionMinutos;

    @NotNull(message = "Debes seleccionar una receta")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECETA_ID", nullable = false)
    private Receta receta;

    public Paso() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroPaso() {
        return numeroPaso;
    }

    public void setNumeroPaso(Integer numeroPaso) {
        this.numeroPaso = numeroPaso;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public Integer getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(Integer duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }
}
