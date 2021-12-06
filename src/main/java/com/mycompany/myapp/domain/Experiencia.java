package com.mycompany.myapp.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Experiencia.
 *
 */

//Comentario Esther:
//	 * Entidad creada desde la consola con el comando jhipster entity experiencia. Servicio asociado a la entidad.
//	 * Esta entidad tiene 4 campos: título, descripción, localización y fecha cada uno con su correspondiente validación.

@Entity
@Table(name = "experiencia")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "experiencia")
public class Experiencia implements Serializable {

    private static final long serialVersionUID = 1L;

    //Id generado automáticamente para la BBDD
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //Campo título de tipo string con validación max-min length y requerido para que no se puedan añadir registros vacíos.
    @NotNull
    @Size(min = 4, max = 50)
    @Column(name = "titulo", length = 50, nullable = false)
    private String titulo;

    //Campo descripción de tipo string con validación max-min length de 200 para que se pueda añadir más información
    @Size(min = 4, max = 200)
    @Column(name = "descripcion", length = 200)
    private String descripcion;

    //Campo localización de tipo string con validación max-min length
    @Size(min = 4, max = 50)
    @Column(name = "localizacion", length = 50)
    private String localizacion;

    //Campo fecha tipo Instant sin validación
    @Column(name = "fecha")
    private Instant fecha;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Experiencia id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public Experiencia titulo(String titulo) {
        this.setTitulo(titulo);
        return this;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public Experiencia descripcion(String descripcion) {
        this.setDescripcion(descripcion);
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLocalizacion() {
        return this.localizacion;
    }

    public Experiencia localizacion(String localizacion) {
        this.setLocalizacion(localizacion);
        return this;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public Instant getFecha() {
        return this.fecha;
    }

    public Experiencia fecha(Instant fecha) {
        this.setFecha(fecha);
        return this;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Experiencia)) {
            return false;
        }
        return id != null && id.equals(((Experiencia) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Experiencia{" +
            "id=" + getId() +
            ", titulo='" + getTitulo() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", localizacion='" + getLocalizacion() + "'" +
            ", fecha='" + getFecha() + "'" +
            "}";
    }
}
