package com.politicasnegocio.backendpoliticas.servicio;

import com.politicasnegocio.backendpoliticas.dto.RespuestaIndicadores;
import com.politicasnegocio.backendpoliticas.enumeracion.EstadoTramite;
import com.politicasnegocio.backendpoliticas.repositorio.RepositorioTramite;
import org.springframework.stereotype.Service;

@Service
public class ServicioIndicador {

    private final RepositorioTramite repositorioTramite;

    public ServicioIndicador(RepositorioTramite repositorioTramite) {
        this.repositorioTramite = repositorioTramite;
    }

    public RespuestaIndicadores obtenerIndicadoresGenerales() {
        long tramitesNuevos = repositorioTramite.countByEstadoActual(EstadoTramite.NUEVO);
        long tramitesEnRevision = repositorioTramite.countByEstadoActual(EstadoTramite.EN_REVISION);
        long tramitesEnEvaluacion = repositorioTramite.countByEstadoActual(EstadoTramite.EN_EVALUACION);
        long tramitesPendientes = repositorioTramite.countByEstadoActual(EstadoTramite.PENDIENTE);
        long tramitesObservados = repositorioTramite.countByEstadoActual(EstadoTramite.OBSERVADO);
        long tramitesRechazados = repositorioTramite.countByEstadoActual(EstadoTramite.RECHAZADO);
        long tramitesAprobados = repositorioTramite.countByEstadoActual(EstadoTramite.APROBADO);
        long tramitesFinalizados = repositorioTramite.countByEstadoActual(EstadoTramite.FINALIZADO);
        long totalTramites = repositorioTramite.count();

        return new RespuestaIndicadores(
                tramitesNuevos,
                tramitesEnRevision,
                tramitesEnEvaluacion,
                tramitesPendientes,
                tramitesObservados,
                tramitesRechazados,
                tramitesAprobados,
                tramitesFinalizados,
                totalTramites
        );
    }

    public boolean existePosibleCuelloBotellaEnDepartamento(String departamentoId) {
        long cantidadPendiente = repositorioTramite.countByDepartamentoActualId(departamentoId);

        return cantidadPendiente >= 5;
    }
}