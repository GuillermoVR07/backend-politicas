package com.politicasnegocio.backendpoliticas.repositorio;

import com.politicasnegocio.backendpoliticas.enumeracion.EstadoTramite;
import com.politicasnegocio.backendpoliticas.modelo.Tramite;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RepositorioTramite extends MongoRepository<Tramite, String> {

    List<Tramite> findByIdentificacionCiudadano(String identificacionCiudadano);

    List<Tramite> findByDepartamentoActualId(String departamentoActualId);

    long countByEstadoActual(EstadoTramite estadoActual);

    long countByDepartamentoActualId(String departamentoActualId);
}