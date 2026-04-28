package com.politicasnegocio.backendpoliticas.modelo;

import com.politicasnegocio.backendpoliticas.enumeracion.TipoDocumentoComunicado;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "documentos_comunicados")
public class DocumentoComunicado {

    @Id
    public String id;

    public String tramiteId;

    public TipoDocumentoComunicado tipo;

    public String nombre;

    public String descripcion;

    public String departamentoId;

    public String nombreDepartamento;

    public boolean visibleParaCliente;

    public String fechaRegistro;
}