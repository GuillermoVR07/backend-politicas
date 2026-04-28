package com.politicasnegocio.backendpoliticas.dto;

import com.politicasnegocio.backendpoliticas.enumeracion.TipoDocumentoComunicado;

public record SolicitudDocumentoComunicado(
        String tramiteId,
        TipoDocumentoComunicado tipo,
        String nombre,
        String descripcion,
        String departamentoId,
        String nombreDepartamento,
        boolean visibleParaCliente
) {
}