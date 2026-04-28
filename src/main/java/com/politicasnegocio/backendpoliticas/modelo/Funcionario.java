package com.politicasnegocio.backendpoliticas.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "funcionarios")
public class Funcionario {

    @Id
    public String id;

    public String nombreCompleto;

    public String cargo;

    public String departamentoId;
}