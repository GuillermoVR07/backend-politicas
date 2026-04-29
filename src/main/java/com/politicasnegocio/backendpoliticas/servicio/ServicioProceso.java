package com.politicasnegocio.backendpoliticas.servicio;

import com.politicasnegocio.backendpoliticas.dto.SolicitudCrearProceso;
import com.politicasnegocio.backendpoliticas.modelo.Proceso;
import com.politicasnegocio.backendpoliticas.repositorio.RepositorioProceso;
import org.springframework.stereotype.Service;

import java.util.List;

import com.politicasnegocio.backendpoliticas.dto.SolicitudActualizarDiagrama;
import com.politicasnegocio.backendpoliticas.modelo.Diagrama;

import java.time.LocalDateTime;

@Service
public class ServicioProceso {

    private final RepositorioProceso repositorioProceso;

    public ServicioProceso(RepositorioProceso repositorioProceso) {
        this.repositorioProceso = repositorioProceso;
    }

    public Proceso crearProceso(SolicitudCrearProceso solicitud) {
        Proceso proceso = new Proceso();
        proceso.nombre = solicitud.nombre();
        proceso.descripcion = solicitud.descripcion();
        proceso.departamentos = solicitud.departamentos();

        return repositorioProceso.save(proceso);
    }

    public List<Proceso> listarProcesos() {
        return repositorioProceso.findAll();
    }

    public Proceso buscarProcesoPorId(String id) {
        return repositorioProceso.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el proceso solicitado"));
    }

    public Proceso actualizarProceso(String id, SolicitudCrearProceso solicitud) {
        Proceso proceso = buscarProcesoPorId(id);
        proceso.nombre = solicitud.nombre();
        proceso.descripcion = solicitud.descripcion();
        proceso.departamentos = solicitud.departamentos();

        return repositorioProceso.save(proceso);
    }

    public Proceso actualizarDiagrama(String id, SolicitudActualizarDiagrama solicitud) {
        Proceso proceso = buscarProcesoPorId(id);

        Diagrama diagrama = new Diagrama();
        diagrama.contenidoXml = solicitud.contenidoXml();
        diagrama.contenidoJson = solicitud.contenidoJson();
        diagrama.generadoPorIa = solicitud.generadoPorIa();
        diagrama.fechaActualizacion = LocalDateTime.now().toString();

        proceso.diagrama = diagrama;

        return repositorioProceso.save(proceso);
    }

    public void eliminarProceso(String id) {
        Proceso proceso = buscarProcesoPorId(id);
        repositorioProceso.delete(proceso);
    }


}