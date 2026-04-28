package com.politicasnegocio.backendpoliticas.repositorio;

import com.politicasnegocio.backendpoliticas.modelo.Funcionario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RepositorioFuncionario extends MongoRepository<Funcionario, String> {

    List<Funcionario> findByDepartamentoId(String departamentoId);
}