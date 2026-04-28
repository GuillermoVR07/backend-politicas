package com.politicasnegocio.backendpoliticas.repositorio;

import com.politicasnegocio.backendpoliticas.modelo.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RepositorioCliente extends MongoRepository<Cliente, String> {

    Optional<Cliente> findByIdentificacionCiudadano(String identificacionCiudadano);
}