package com.incidentflow.controller;

import com.incidentflow.dto.IncidenteDTO;
import com.incidentflow.model.Incidente;
import com.incidentflow.service.IncidenteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidentes")
@CrossOrigin("*")
public class IncidenteController {

    private final IncidenteService incidenteService;

    public IncidenteController(IncidenteService incidenteService) {
        this.incidenteService = incidenteService;
    }

    @GetMapping
    public List<Incidente> listar() {
        return incidenteService.listar();
    }

    @GetMapping("/{id}")
    public Incidente buscarPorId(@PathVariable Long id) {
        return incidenteService.buscarPorId(id);
    }

    @PostMapping
    public Incidente guardar(@RequestBody IncidenteDTO dto) {
        return incidenteService.guardar(dto);
    }

    @PutMapping("/{id}")
    public Incidente actualizar(@PathVariable Long id, @RequestBody IncidenteDTO dto) {
        return incidenteService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        incidenteService.eliminar(id);
    }
}