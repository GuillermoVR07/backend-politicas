package com.politicasnegocio.backendpoliticas.servicio;

import com.politicasnegocio.backendpoliticas.modelo.Departamento;
import com.politicasnegocio.backendpoliticas.repositorio.RepositorioDepartamento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioDepartamento {

    private final RepositorioDepartamento repositorioDepartamento;

    public ServicioDepartamento(RepositorioDepartamento repositorioDepartamento) {
        this.repositorioDepartamento = repositorioDepartamento;
    }

    public Departamento crearDepartamento(Departamento departamento) {
        return repositorioDepartamento.save(departamento);
    }

    public List<Departamento> listarDepartamentos() {
        return repositorioDepartamento.findAll();
    }

    public Departamento buscarDepartamentoPorId(String id) {
        return repositorioDepartamento.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el departamento solicitado"));
    }

    public Departamento actualizarDepartamento(String id, Departamento datosActualizados) {
        Departamento departamento = buscarDepartamentoPorId(id);

        departamento.nombre = datosActualizados.nombre;
        departamento.descripcion = datosActualizados.descripcion;

        return repositorioDepartamento.save(departamento);
    }
}