package com.politicasnegocio.backendpoliticas.repositorio;

import com.politicasnegocio.backendpoliticas.modelo.Proceso;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioProceso extends MongoRepository<Proceso, String> {
}