package com.politicasnegocio.backendpoliticas.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "departamentos")
public class Departamento {

    @Id
    public String id;

    public String nombre;

    public String descripcion;
}