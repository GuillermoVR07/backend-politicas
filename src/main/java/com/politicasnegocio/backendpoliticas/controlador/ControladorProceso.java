package com.politicasnegocio.backendpoliticas.controlador;

import com.politicasnegocio.backendpoliticas.dto.SolicitudCrearProceso;
import com.politicasnegocio.backendpoliticas.modelo.Proceso;
import com.politicasnegocio.backendpoliticas.servicio.ServicioProceso;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.politicasnegocio.backendpoliticas.dto.SolicitudActualizarDiagrama;

@RestController
@RequestMapping("/api/procesos")
public class ControladorProceso {

    private final ServicioProceso servicioProceso;

    public ControladorProceso(ServicioProceso servicioProceso) {
        this.servicioProceso = servicioProceso;
    }

    @PostMapping
    public Proceso crearProceso(@RequestBody SolicitudCrearProceso solicitud) {
        return servicioProceso.crearProceso(solicitud);
    }

    @GetMapping
    public List<Proceso> listarProcesos() {
        return servicioProceso.listarProcesos();
    }

    @GetMapping("/{id}")
    public Proceso buscarProceso(@PathVariable String id) {
        return servicioProceso.buscarProcesoPorId(id);
    }

    @PutMapping("/{id}")
    public Proceso actualizarProceso(
            @PathVariable String id,
            @RequestBody SolicitudCrearProceso solicitud
    ) {
        return servicioProceso.actualizarProceso(id, solicitud);
    }

    @PutMapping("/{id}/diagrama")
    public Proceso actualizarDiagrama(
            @PathVariable String id,
            @RequestBody SolicitudActualizarDiagrama solicitud
    ) {
        return servicioProceso.actualizarDiagrama(id, solicitud);
    }

    @DeleteMapping("/{id}")
    public void eliminarProceso(@PathVariable String id) {
        servicioProceso.eliminarProceso(id);
    }

}