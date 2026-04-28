package com.politicasnegocio.backendpoliticas.controlador;

import com.politicasnegocio.backendpoliticas.dto.SolicitudGenerarDiagrama;
import com.politicasnegocio.backendpoliticas.servicio.ServicioIa;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ia")
public class ControladorIa {

    private final ServicioIa servicioIa;

    public ControladorIa(ServicioIa servicioIa) {
        this.servicioIa = servicioIa;
    }

    @PostMapping("/generar-diagrama")
    public Object generarBorradorDiagrama(@RequestBody SolicitudGenerarDiagrama solicitud) {
        return servicioIa.generarBorradorDiagrama(solicitud);
    }
}