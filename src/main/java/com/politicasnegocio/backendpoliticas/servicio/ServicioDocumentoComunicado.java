package com.politicasnegocio.backendpoliticas.servicio;

import com.politicasnegocio.backendpoliticas.dto.SolicitudDocumentoComunicado;
import com.politicasnegocio.backendpoliticas.modelo.DocumentoComunicado;
import com.politicasnegocio.backendpoliticas.repositorio.RepositorioDocumentoComunicado;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServicioDocumentoComunicado {

    private final RepositorioDocumentoComunicado repositorioDocumentoComunicado;

    public ServicioDocumentoComunicado(RepositorioDocumentoComunicado repositorioDocumentoComunicado) {
        this.repositorioDocumentoComunicado = repositorioDocumentoComunicado;
    }

    public DocumentoComunicado crearDocumentoComunicado(SolicitudDocumentoComunicado solicitud) {
        DocumentoComunicado documentoComunicado = new DocumentoComunicado();

        documentoComunicado.tramiteId = solicitud.tramiteId();
        documentoComunicado.tipo = solicitud.tipo();
        documentoComunicado.nombre = solicitud.nombre();
        documentoComunicado.descripcion = solicitud.descripcion();
        documentoComunicado.departamentoId = solicitud.departamentoId();
        documentoComunicado.nombreDepartamento = solicitud.nombreDepartamento();
        documentoComunicado.visibleParaCliente = solicitud.visibleParaCliente();
        documentoComunicado.fechaRegistro = LocalDateTime.now().toString();

        return repositorioDocumentoComunicado.save(documentoComunicado);
    }

    public List<DocumentoComunicado> listarPorTramite(String tramiteId) {
        return repositorioDocumentoComunicado.findByTramiteId(tramiteId);
    }

    public List<DocumentoComunicado> listarVisiblesParaCliente(String tramiteId) {
        return repositorioDocumentoComunicado.findByTramiteIdAndVisibleParaCliente(tramiteId, true);
    }
}