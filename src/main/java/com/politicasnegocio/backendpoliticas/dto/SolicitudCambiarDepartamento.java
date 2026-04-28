package com.politicasnegocio.backendpoliticas.dto;

public record SolicitudCambiarDepartamento(
        String nuevoDepartamentoId,
        String nombreNuevoDepartamento,
        String observacion,
        boolean visibleParaCliente
) {
}