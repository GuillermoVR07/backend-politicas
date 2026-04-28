package com.politicasnegocio.backendpoliticas.modelo;

import com.politicasnegocio.backendpoliticas.enumeracion.EstadoTramite;

public class SeguimientoTramite {

    public String departamentoId;

    public String nombreDepartamento;

    public EstadoTramite estado;

    public String observacion;

    public String fechaRegistro;

    public boolean visibleParaCliente;
}