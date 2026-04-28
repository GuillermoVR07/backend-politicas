package com.politicasnegocio.backendpoliticas.dto;

import java.util.List;

public record SolicitudCrearProceso(
        String nombre,
        String descripcion,
        List<String> departamentos
) {
}