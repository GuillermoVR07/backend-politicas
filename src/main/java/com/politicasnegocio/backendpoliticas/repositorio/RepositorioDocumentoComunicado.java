package com.politicasnegocio.backendpoliticas.repositorio;

import com.politicasnegocio.backendpoliticas.modelo.DocumentoComunicado;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RepositorioDocumentoComunicado extends MongoRepository<DocumentoComunicado, String> {

    List<DocumentoComunicado> findByTramiteId(String tramiteId);

    List<DocumentoComunicado> findByTramiteIdAndVisibleParaCliente(String tramiteId, boolean visibleParaCliente);
}