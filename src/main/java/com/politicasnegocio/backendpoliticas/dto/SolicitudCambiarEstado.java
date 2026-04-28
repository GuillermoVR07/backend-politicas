package com.politicasnegocio.backendpoliticas.dto;

import com.politicasnegocio.backendpoliticas.enumeracion.EstadoTramite;

public record SolicitudCambiarEstado(
        EstadoTramite nuevoEstado,
        String observacion,
        boolean visibleParaCliente
) {
}