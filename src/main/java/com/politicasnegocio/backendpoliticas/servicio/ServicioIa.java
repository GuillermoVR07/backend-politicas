package com.politicasnegocio.backendpoliticas.servicio;

import com.politicasnegocio.backendpoliticas.dto.SolicitudGenerarDiagrama;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ServicioIa {

    @Value("${servicio.ia.url}")
    private String urlServicioIa;

    private final RestTemplate restTemplate = new RestTemplate();

    public Object generarBorradorDiagrama(SolicitudGenerarDiagrama solicitud) {
        String url = urlServicioIa + "/ia/generar-diagrama";

        try {
            return restTemplate.postForObject(url, solicitud, Object.class);
        } catch (Exception error) {
            return Map.of(
                    "mensaje", "No se pudo conectar con el servicio de IA",
                    "detalle", error.getMessage()
            );
        }
    }
}