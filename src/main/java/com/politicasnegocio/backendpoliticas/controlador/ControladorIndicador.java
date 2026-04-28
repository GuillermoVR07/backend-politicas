package com.politicasnegocio.backendpoliticas.controlador;

import com.politicasnegocio.backendpoliticas.dto.RespuestaIndicadores;
import com.politicasnegocio.backendpoliticas.servicio.ServicioIndicador;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/indicadores")
public class ControladorIndicador {

    private final ServicioIndicador servicioIndicador;

    public ControladorIndicador(ServicioIndicador servicioIndicador) {
        this.servicioIndicador = servicioIndicador;
    }

    @GetMapping("/generales")
    public RespuestaIndicadores obtenerIndicadoresGenerales() {
        return servicioIndicador.obtenerIndicadoresGenerales();
    }

    @GetMapping("/cuello-botella/departamento/{departamentoId}")
    public Map<String, Object> verificarCuelloBotella(@PathVariable String departamentoId) {
        boolean existeCuelloBotella =
                servicioIndicador.existePosibleCuelloBotellaEnDepartamento(departamentoId);

        return Map.of(
                "departamentoId", departamentoId,
                "posibleCuelloBotella", existeCuelloBotella
        );
    }
}