package com.incidentflow.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IncidenteDTO {
    private String titulo;
    private String descripcion;
    private String prioridad;
    private String estadoActual;
    private Long usuarioReportaId;
    private Long categoriaId;
}