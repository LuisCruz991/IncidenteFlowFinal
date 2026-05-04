package com.incidentflow.service;

import com.incidentflow.dto.IncidenteDTO;
import com.incidentflow.exception.ResourceNotFoundException;
import com.incidentflow.model.Categoria;
import com.incidentflow.model.Incidente;
import com.incidentflow.model.Usuario;
import com.incidentflow.repository.CategoriaRepository;
import com.incidentflow.repository.IncidenteRepository;
import com.incidentflow.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidenteService {

    private final IncidenteRepository incidenteRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;

    public IncidenteService(
            IncidenteRepository incidenteRepository,
            UsuarioRepository usuarioRepository,
            CategoriaRepository categoriaRepository
    ) {
        this.incidenteRepository = incidenteRepository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Incidente> listar() {
        return incidenteRepository.findAll();
    }

    public Incidente buscarPorId(Long id) {
        return incidenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incidente no encontrado"));
    }

    public Incidente guardar(IncidenteDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioReportaId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

        Incidente incidente = new Incidente();
        incidente.setTitulo(dto.getTitulo());
        incidente.setDescripcion(dto.getDescripcion());
        incidente.setPrioridad(dto.getPrioridad());
        incidente.setEstadoActual(dto.getEstadoActual() != null ? dto.getEstadoActual() : "ABIERTO");
        incidente.setUsuarioReporta(usuario);
        incidente.setCategoria(categoria);

        return incidenteRepository.save(incidente);
    }

    public Incidente actualizar(Long id, IncidenteDTO dto) {
        Incidente incidente = buscarPorId(id);

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioReportaId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

        incidente.setTitulo(dto.getTitulo());
        incidente.setDescripcion(dto.getDescripcion());
        incidente.setPrioridad(dto.getPrioridad());
        incidente.setEstadoActual(dto.getEstadoActual());
        incidente.setUsuarioReporta(usuario);
        incidente.setCategoria(categoria);

        return incidenteRepository.save(incidente);
    }

    public void eliminar(Long id) {
        Incidente incidente = buscarPorId(id);
        incidenteRepository.delete(incidente);
    }
}