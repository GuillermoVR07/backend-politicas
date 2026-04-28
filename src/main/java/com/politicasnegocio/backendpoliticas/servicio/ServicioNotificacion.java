package com.politicasnegocio.backendpoliticas.servicio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ServicioNotificacion {

    @Value("${notificaciones.firebase.habilitadas:false}")
    private boolean firebaseHabilitado;

    public void notificarCambioEstadoODepartamento(
            String identificacionCiudadano,
            String titulo,
            String mensaje
    ) {
        if (!firebaseHabilitado) {
            System.out.println("Notificación simulada para ciudadano "
                    + identificacionCiudadano
                    + ": "
                    + titulo
                    + " - "
                    + mensaje);
            return;
        }

        /*
         * La integración real con Firebase Cloud Messaging
         * se completará en la fase de integración.
         */
    }
}