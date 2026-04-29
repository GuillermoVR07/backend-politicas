package com.politicasnegocio.backendpoliticas.servicio;

import com.politicasnegocio.backendpoliticas.dto.SolicitudCambiarDepartamento;
import com.politicasnegocio.backendpoliticas.dto.SolicitudCambiarEstado;
import com.politicasnegocio.backendpoliticas.dto.SolicitudCrearTramite;
import com.politicasnegocio.backendpoliticas.enumeracion.EstadoTramite;
import com.politicasnegocio.backendpoliticas.modelo.SeguimientoTramite;
import com.politicasnegocio.backendpoliticas.modelo.Tramite;
import com.politicasnegocio.backendpoliticas.repositorio.RepositorioTramite;
import com.politicasnegocio.backendpoliticas.dto.SolicitudActualizarTramite;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServicioTramite {

    private final RepositorioTramite repositorioTramite;
    private final ServicioNotificacion servicioNotificacion;

    public ServicioTramite(
            RepositorioTramite repositorioTramite,
            ServicioNotificacion servicioNotificacion
    ) {
        this.repositorioTramite = repositorioTramite;
        this.servicioNotificacion = servicioNotificacion;
    }

    public Tramite crearTramite(SolicitudCrearTramite solicitud) {
        Tramite tramite = new Tramite();

        tramite.codigo = solicitud.codigo();
        tramite.titulo = solicitud.titulo();
        tramite.descripcion = solicitud.descripcion();
        tramite.identificacionCiudadano = solicitud.identificacionCiudadano();
        tramite.procesoId = solicitud.procesoId();
        tramite.estadoActual = EstadoTramite.NUEVO;
        tramite.departamentoActualId = solicitud.departamentoInicialId();
        tramite.nombreDepartamentoActual = solicitud.nombreDepartamentoInicial();
        tramite.fechaCreacion = LocalDateTime.now().toString();
        tramite.fechaUltimaActualizacion = LocalDateTime.now().toString();

        SeguimientoTramite seguimientoInicial = new SeguimientoTramite();
        seguimientoInicial.departamentoId = solicitud.departamentoInicialId();
        seguimientoInicial.nombreDepartamento = solicitud.nombreDepartamentoInicial();
        seguimientoInicial.estado = EstadoTramite.NUEVO;
        seguimientoInicial.observacion = "Trámite registrado inicialmente";
        seguimientoInicial.fechaRegistro = LocalDateTime.now().toString();
        seguimientoInicial.visibleParaCliente = true;

        tramite.seguimientos.add(seguimientoInicial);

        return repositorioTramite.save(tramite);
    }

    public List<Tramite> listarTramites() {
        return repositorioTramite.findAll();
    }

    public Tramite buscarTramitePorId(String id) {
        return repositorioTramite.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el trámite solicitado"));
    }

    public List<Tramite> buscarTramitesPorCiudadano(String identificacionCiudadano) {
        return repositorioTramite.findByIdentificacionCiudadano(identificacionCiudadano);
    }

    public List<Tramite> buscarTramitesPorDepartamento(String departamentoId) {
        return repositorioTramite.findByDepartamentoActualId(departamentoId);
    }

    public Tramite cambiarEstado(String tramiteId, SolicitudCambiarEstado solicitud) {
        Tramite tramite = buscarTramitePorId(tramiteId);

        tramite.estadoActual = solicitud.nuevoEstado();
        tramite.fechaUltimaActualizacion = LocalDateTime.now().toString();

        SeguimientoTramite seguimiento = new SeguimientoTramite();
        seguimiento.departamentoId = tramite.departamentoActualId;
        seguimiento.nombreDepartamento = tramite.nombreDepartamentoActual;
        seguimiento.estado = solicitud.nuevoEstado();
        seguimiento.observacion = solicitud.observacion();
        seguimiento.fechaRegistro = LocalDateTime.now().toString();
        seguimiento.visibleParaCliente = solicitud.visibleParaCliente();

        tramite.seguimientos.add(seguimiento);

        Tramite tramiteGuardado = repositorioTramite.save(tramite);

        servicioNotificacion.notificarCambioEstadoODepartamento(
                tramite.identificacionCiudadano,
                "Cambio de estado del trámite",
                "Su trámite cambió de estado a: " + solicitud.nuevoEstado()
        );

        return tramiteGuardado;
    }

    public Tramite cambiarDepartamento(String tramiteId, SolicitudCambiarDepartamento solicitud) {
        Tramite tramite = buscarTramitePorId(tramiteId);

        tramite.departamentoActualId = solicitud.nuevoDepartamentoId();
        tramite.nombreDepartamentoActual = solicitud.nombreNuevoDepartamento();
        tramite.estadoActual = EstadoTramite.DERIVADO_A_OTRO_DEPARTAMENTO;
        tramite.fechaUltimaActualizacion = LocalDateTime.now().toString();

        SeguimientoTramite seguimiento = new SeguimientoTramite();
        seguimiento.departamentoId = solicitud.nuevoDepartamentoId();
        seguimiento.nombreDepartamento = solicitud.nombreNuevoDepartamento();
        seguimiento.estado = EstadoTramite.DERIVADO_A_OTRO_DEPARTAMENTO;
        seguimiento.observacion = solicitud.observacion();
        seguimiento.fechaRegistro = LocalDateTime.now().toString();
        seguimiento.visibleParaCliente = solicitud.visibleParaCliente();

        tramite.seguimientos.add(seguimiento);

        Tramite tramiteGuardado = repositorioTramite.save(tramite);

        servicioNotificacion.notificarCambioEstadoODepartamento(
                tramite.identificacionCiudadano,
                "Cambio de departamento del trámite",
                "Su trámite fue derivado al departamento: " + solicitud.nombreNuevoDepartamento()
        );

        return tramiteGuardado;
    }


    public Tramite actualizarTramite(String tramiteId, SolicitudActualizarTramite solicitud) {
        Tramite tramite = buscarTramitePorId(tramiteId);

        tramite.codigo = solicitud.codigo();
        tramite.titulo = solicitud.titulo();
        tramite.descripcion = solicitud.descripcion();
        tramite.identificacionCiudadano = solicitud.identificacionCiudadano();
        tramite.procesoId = solicitud.procesoId();
        tramite.fechaUltimaActualizacion = LocalDateTime.now().toString();

        return repositorioTramite.save(tramite);
    }

    public void eliminarTramite(String tramiteId) {
        Tramite tramite = buscarTramitePorId(tramiteId);
        repositorioTramite.delete(tramite);
    }


}