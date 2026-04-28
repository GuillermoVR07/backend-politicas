package com.politicasnegocio.backendpoliticas.controlador;

import com.politicasnegocio.backendpoliticas.modelo.Departamento;
import com.politicasnegocio.backendpoliticas.servicio.ServicioDepartamento;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class ControladorDepartamento {

    private final ServicioDepartamento servicioDepartamento;

    public ControladorDepartamento(ServicioDepartamento servicioDepartamento) {
        this.servicioDepartamento = servicioDepartamento;
    }

    @PostMapping
    public Departamento crearDepartamento(@RequestBody Departamento departamento) {
        return servicioDepartamento.crearDepartamento(departamento);
    }

    @GetMapping
    public List<Departamento> listarDepartamentos() {
        return servicioDepartamento.listarDepartamentos();
    }

    @GetMapping("/{id}")
    public Departamento buscarDepartamento(@PathVariable String id) {
        return servicioDepartamento.buscarDepartamentoPorId(id);
    }

    @PutMapping("/{id}")
    public Departamento actualizarDepartamento(
            @PathVariable String id,
            @RequestBody Departamento departamento
    ) {
        return servicioDepartamento.actualizarDepartamento(id, departamento);
    }
}