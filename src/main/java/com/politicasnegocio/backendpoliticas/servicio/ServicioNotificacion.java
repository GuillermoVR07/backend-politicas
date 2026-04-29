package com.politicasnegocio.backendpoliticas.servicio;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.politicasnegocio.backendpoliticas.modelo.Cliente;
import com.politicasnegocio.backendpoliticas.repositorio.RepositorioCliente;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Optional;

@Service
public class ServicioNotificacion {

    private final RepositorioCliente repositorioCliente;

    @Value("${notificaciones.firebase.habilitadas:false}")
    private boolean firebaseHabilitado;

    @Value("${notificaciones.firebase.ruta-credenciales:firebase/firebase-service-account.json}")
    private String rutaCredencialesFirebase;

    public ServicioNotificacion(RepositorioCliente repositorioCliente) {
        this.repositorioCliente = repositorioCliente;
    }

    @PostConstruct
    public void inicializarFirebase() {
        if (!firebaseHabilitado) {
            System.out.println("Firebase Cloud Messaging está deshabilitado. Se usarán notificaciones simuladas.");
            return;
        }

        try {
            if (!FirebaseApp.getApps().isEmpty()) {
                System.out.println("Firebase ya fue inicializado.");
                return;
            }

            ClassPathResource recursoCredenciales = new ClassPathResource(rutaCredencialesFirebase);

            try (InputStream archivoCredenciales = recursoCredenciales.getInputStream()) {
                FirebaseOptions opciones = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(archivoCredenciales))
                        .build();

                FirebaseApp.initializeApp(opciones);

                System.out.println("Firebase Cloud Messaging inicializado correctamente.");
            }
        } catch (Exception error) {
            System.out.println("No se pudo inicializar Firebase Cloud Messaging: " + error.getMessage());
        }
    }

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

        Optional<Cliente> clienteEncontrado =
                repositorioCliente.findByIdentificacionCiudadano(identificacionCiudadano);

        if (clienteEncontrado.isEmpty()) {
            System.out.println("No se encontró cliente con identificación: " + identificacionCiudadano);
            return;
        }

        Cliente cliente = clienteEncontrado.get();

        if (cliente.tokenNotificacionMovil == null || cliente.tokenNotificacionMovil.isBlank()) {
            System.out.println("El cliente no tiene token de notificación registrado: " + identificacionCiudadano);
            return;
        }

        enviarNotificacionAToken(
                cliente.tokenNotificacionMovil,
                titulo,
                mensaje,
                identificacionCiudadano
        );
    }

    private void enviarNotificacionAToken(
            String tokenNotificacionMovil,
            String titulo,
            String mensaje,
            String identificacionCiudadano
    ) {
        try {
            Message mensajeFirebase = Message.builder()
                    .setToken(tokenNotificacionMovil)
                    .setNotification(
                            Notification.builder()
                                    .setTitle(titulo)
                                    .setBody(mensaje)
                                    .build()
                    )
                    .putData("identificacionCiudadano", identificacionCiudadano)
                    .putData("origen", "BACKEND_POLITICAS")
                    .build();

            String respuesta = FirebaseMessaging.getInstance().send(mensajeFirebase);

            System.out.println("Notificación enviada correctamente. Respuesta Firebase: " + respuesta);
        } catch (Exception error) {
            System.out.println("No se pudo enviar la notificación Firebase: " + error.getMessage());
        }
    }
}