package com.politicasnegocio.backendpoliticas.controlador;

import com.politicasnegocio.backendpoliticas.dto.SolicitudDocumentoComunicado;
import com.politicasnegocio.backendpoliticas.modelo.DocumentoComunicado;
import com.politicasnegocio.backendpoliticas.servicio.ServicioDocumentoComunicado;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documentos-comunicados")
public class ControladorDocumentoComunicado {

    private final ServicioDocumentoComunicado servicioDocumentoComunicado;

    public ControladorDocumentoComunicado(ServicioDocumentoComunicado servicioDocumentoComunicado) {
        this.servicioDocumentoComunicado = servicioDocumentoComunicado;
    }

    @PostMapping
    public DocumentoComunicado crearDocumentoComunicado(
            @RequestBody SolicitudDocumentoComunicado solicitud
    ) {
        return servicioDocumentoComunicado.crearDocumentoComunicado(solicitud);
    }

    @GetMapping("/tramite/{tramiteId}")
    public List<DocumentoComunicado> listarPorTramite(@PathVariable String tramiteId) {
        return servicioDocumentoComunicado.listarPorTramite(tramiteId);
    }

    @GetMapping("/tramite/{tramiteId}/visibles-cliente")
    public List<DocumentoComunicado> listarVisiblesParaCliente(@PathVariable String tramiteId) {
        return servicioDocumentoComunicado.listarVisiblesParaCliente(tramiteId);
    }
}