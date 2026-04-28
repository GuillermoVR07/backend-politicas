package com.politicasnegocio.backendpoliticas.dto;

public record SolicitudCrearTramite(
        String codigo,
        String titulo,
        String descripcion,
        String identificacionCiudadano,
        String procesoId,
        String departamentoInicialId,
        String nombreDepartamentoInicial
) {
}