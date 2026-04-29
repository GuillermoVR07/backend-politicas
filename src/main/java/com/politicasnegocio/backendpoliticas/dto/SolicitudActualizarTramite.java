package com.politicasnegocio.backendpoliticas.dto;

public record SolicitudActualizarTramite(
        String codigo,
        String titulo,
        String descripcion,
        String identificacionCiudadano,
        String procesoId
) {
}