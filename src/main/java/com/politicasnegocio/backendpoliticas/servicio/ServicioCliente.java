package com.politicasnegocio.backendpoliticas.servicio;

import com.politicasnegocio.backendpoliticas.dto.SolicitudRegistrarToken;
import com.politicasnegocio.backendpoliticas.modelo.Cliente;
import com.politicasnegocio.backendpoliticas.repositorio.RepositorioCliente;
import org.springframework.stereotype.Service;

@Service
public class ServicioCliente {

    private final RepositorioCliente repositorioCliente;

    public ServicioCliente(RepositorioCliente repositorioCliente) {
        this.repositorioCliente = repositorioCliente;
    }

    public Cliente registrarTokenNotificacion(SolicitudRegistrarToken solicitud) {
        Cliente cliente = repositorioCliente
                .findByIdentificacionCiudadano(solicitud.identificacionCiudadano())
                .orElseGet(() -> {
                    Cliente nuevoCliente = new Cliente();
                    nuevoCliente.identificacionCiudadano = solicitud.identificacionCiudadano();
                    nuevoCliente.nombreCompleto = "Cliente de demostración";
                    return nuevoCliente;
                });

        cliente.tokenNotificacionMovil = solicitud.tokenNotificacionMovil();

        return repositorioCliente.save(cliente);
    }
}