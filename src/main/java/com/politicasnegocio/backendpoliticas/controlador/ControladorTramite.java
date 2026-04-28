package com.politicasnegocio.backendpoliticas.controlador;

import com.politicasnegocio.backendpoliticas.dto.SolicitudCambiarDepartamento;
import com.politicasnegocio.backendpoliticas.dto.SolicitudCambiarEstado;
import com.politicasnegocio.backendpoliticas.dto.SolicitudCrearTramite;
import com.politicasnegocio.backendpoliticas.modelo.Tramite;
import com.politicasnegocio.backendpoliticas.servicio.ServicioTramite;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tramites")
public class ControladorTramite {

    private final ServicioTramite servicioTramite;

    public ControladorTramite(ServicioTramite servicioTramite) {
        this.servicioTramite = servicioTramite;
    }

    @PostMapping
    public Tramite crearTramite(@RequestBody SolicitudCrearTramite solicitud) {
        return servicioTramite.crearTramite(solicitud);
    }

    @GetMapping
    public List<Tramite> listarTramites() {
        return servicioTramite.listarTramites();
    }

    @GetMapping("/{id}")
    public Tramite buscarTramite(@PathVariable String id) {
        return servicioTramite.buscarTramitePorId(id);
    }

    @GetMapping("/ciudadano/{identificacionCiudadano}")
    public List<Tramite> buscarPorCiudadano(@PathVariable String identificacionCiudadano) {
        return servicioTramite.buscarTramitesPorCiudadano(identificacionCiudadano);
    }

    @GetMapping("/departamento/{departamentoId}")
    public List<Tramite> buscarPorDepartamento(@PathVariable String departamentoId) {
        return servicioTramite.buscarTramitesPorDepartamento(departamentoId);
    }

    @PutMapping("/{id}/cambiar-estado")
    public Tramite cambiarEstado(
            @PathVariable String id,
            @RequestBody SolicitudCambiarEstado solicitud
    ) {
        return servicioTramite.cambiarEstado(id, solicitud);
    }

    @PutMapping("/{id}/cambiar-departamento")
    public Tramite cambiarDepartamento(
            @PathVariable String id,
            @RequestBody SolicitudCambiarDepartamento solicitud
    ) {
        return servicioTramite.cambiarDepartamento(id, solicitud);
    }
}