package com.politicasnegocio.backendpoliticas.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clientes")
public class Cliente {

    @Id
    public String id;

    public String identificacionCiudadano;

    public String nombreCompleto;

    public String tokenNotificacionMovil;
}