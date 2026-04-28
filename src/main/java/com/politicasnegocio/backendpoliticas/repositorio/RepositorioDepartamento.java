package com.politicasnegocio.backendpoliticas.repositorio;

import com.politicasnegocio.backendpoliticas.modelo.Departamento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioDepartamento extends MongoRepository<Departamento, String> {
}