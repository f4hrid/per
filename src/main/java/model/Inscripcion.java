/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Fahrid
 */
@Entity
@Table(name = "inscripcion")
@NamedQueries({
    @NamedQuery(name = "Inscripcion.findAll", query = "SELECT i FROM Inscripcion i"),
    @NamedQuery(name = "Inscripcion.findByFechaInscripcion", query = "SELECT i FROM Inscripcion i WHERE i.fechaInscripcion = :fechaInscripcion"),
    @NamedQuery(name = "Inscripcion.findByIdEstudiante", query = "SELECT i FROM Inscripcion i WHERE i.inscripcionPK.idEstudiante = :idEstudiante"),
    @NamedQuery(name = "Inscripcion.findByIdOferta", query = "SELECT i FROM Inscripcion i WHERE i.inscripcionPK.idOferta = :idOferta")})
public class Inscripcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InscripcionPK inscripcionPK;
    @Basic(optional = false)
    @Column(name = "fecha_inscripcion")
    @Temporal(TemporalType.DATE)
    private Date fechaInscripcion;
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id_estudiante", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Estudiante estudiante;
    @JoinColumn(name = "id_oferta", referencedColumnName = "id_oferta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ofertas ofertas;

    public Inscripcion() {
    }

    public Inscripcion(InscripcionPK inscripcionPK) {
        this.inscripcionPK = inscripcionPK;
    }

    public Inscripcion(InscripcionPK inscripcionPK, Date fechaInscripcion) {
        this.inscripcionPK = inscripcionPK;
        this.fechaInscripcion = fechaInscripcion;
    }

    public Inscripcion(int idEstudiante, int idOferta) {
        this.inscripcionPK = new InscripcionPK(idEstudiante, idOferta);
    }

    public InscripcionPK getInscripcionPK() {
        return inscripcionPK;
    }

    public void setInscripcionPK(InscripcionPK inscripcionPK) {
        this.inscripcionPK = inscripcionPK;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Ofertas getOfertas() {
        return ofertas;
    }

    public void setOfertas(Ofertas ofertas) {
        this.ofertas = ofertas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inscripcionPK != null ? inscripcionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inscripcion)) {
            return false;
        }
        Inscripcion other = (Inscripcion) object;
        if ((this.inscripcionPK == null && other.inscripcionPK != null) || (this.inscripcionPK != null && !this.inscripcionPK.equals(other.inscripcionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Inscripcion[ inscripcionPK=" + inscripcionPK + " ]";
    }
    
}
