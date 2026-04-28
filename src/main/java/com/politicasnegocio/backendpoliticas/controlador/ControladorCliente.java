package com.politicasnegocio.backendpoliticas.controlador;

import com.politicasnegocio.backendpoliticas.dto.SolicitudRegistrarToken;
import com.politicasnegocio.backendpoliticas.modelo.Cliente;
import com.politicasnegocio.backendpoliticas.servicio.ServicioCliente;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ControladorCliente {

    private final ServicioCliente servicioCliente;

    public ControladorCliente(ServicioCliente servicioCliente) {
        this.servicioCliente = servicioCliente;
    }

    @PostMapping("/registrar-token")
    public Cliente registrarToken(@RequestBody SolicitudRegistrarToken solicitud) {
        return servicioCliente.registrarTokenNotificacion(solicitud);
    }
}