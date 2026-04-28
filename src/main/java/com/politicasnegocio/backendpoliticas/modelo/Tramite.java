package com.politicasnegocio.backendpoliticas.modelo;

import com.politicasnegocio.backendpoliticas.enumeracion.EstadoTramite;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "tramites")
public class Tramite {

    @Id
    public String id;

    public String codigo;

    public String titulo;

    public String descripcion;

    public String identificacionCiudadano;

    public String procesoId;

    public EstadoTramite estadoActual;

    public String departamentoActualId;

    public String nombreDepartamentoActual;

    public String fechaCreacion;

    public String fechaUltimaActualizacion;

    public List<SeguimientoTramite> seguimientos = new ArrayList<>();
}