package com.incidentflow.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "incidentes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Incidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "incidente_id")
    private Long incidenteId;

    private String titulo;

    private String descripcion;

    private String prioridad;

    @Column(name = "estado_actual")
    private String estadoActual = "ABIERTO";

    @ManyToOne
    @JoinColumn(name = "usuario_reporta_id")
    private Usuario usuarioReporta;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();
}