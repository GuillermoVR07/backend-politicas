package com.politicasnegocio.backendpoliticas.dto;

public record RespuestaIndicadores(
        long tramitesNuevos,
        long tramitesEnRevision,
        long tramitesEnEvaluacion,
        long tramitesPendientes,
        long tramitesObservados,
        long tramitesRechazados,
        long tramitesAprobados,
        long tramitesFinalizados,
        long totalTramites
) {
}