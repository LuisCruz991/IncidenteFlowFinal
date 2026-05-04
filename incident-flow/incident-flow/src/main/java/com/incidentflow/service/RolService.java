package com.incidentflow.service;

import com.incidentflow.dto.RolDTO;
import com.incidentflow.exception.ResourceNotFoundException;
import com.incidentflow.model.Rol;
import com.incidentflow.repository.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public List<Rol> listar() {
        return rolRepository.findAll();
    }

    public Rol buscarPorId(Long id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));
    }

    public Rol guardar(RolDTO dto) {
        Rol rol = new Rol();
        rol.setNombreRol(dto.getNombreRol());
        rol.setDescripcion(dto.getDescripcion());
        return rolRepository.save(rol);
    }

    public Rol actualizar(Long id, RolDTO dto) {
        Rol rol = buscarPorId(id);
        rol.setNombreRol(dto.getNombreRol());
        rol.setDescripcion(dto.getDescripcion());
        return rolRepository.save(rol);
    }

    public void eliminar(Long id) {
        Rol rol = buscarPorId(id);
        rolRepository.delete(rol);
    }
}