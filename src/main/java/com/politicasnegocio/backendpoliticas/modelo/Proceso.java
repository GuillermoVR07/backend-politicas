package com.politicasnegocio.backendpoliticas.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "procesos")
public class Proceso {

    @Id
    public String id;

    public String nombre;

    public String descripcion;

    public List<String> departamentos = new ArrayList<>();

    public Diagrama diagrama;
}