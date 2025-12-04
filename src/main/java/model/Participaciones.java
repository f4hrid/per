/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "participaciones")
@NamedQueries({
    @NamedQuery(name = "Participaciones.findAll", query = "SELECT p FROM Participaciones p"),
    @NamedQuery(name = "Participaciones.findByIdParticipacion", query = "SELECT p FROM Participaciones p WHERE p.idParticipacion = :idParticipacion"),
    @NamedQuery(name = "Participaciones.findByFechaAsistencia", query = "SELECT p FROM Participaciones p WHERE p.fechaAsistencia = :fechaAsistencia"),
    @NamedQuery(name = "Participaciones.findByHoraAsistencia", query = "SELECT p FROM Participaciones p WHERE p.horaAsistencia = :horaAsistencia"),
    @NamedQuery(name = "Participaciones.findByCalificacionAprobacion", query = "SELECT p FROM Participaciones p WHERE p.calificacionAprobacion = :calificacionAprobacion")})
public class Participaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_participacion")
    private Integer idParticipacion;
    @Column(name = "fecha_asistencia")
    @Temporal(TemporalType.DATE)
    private Date fechaAsistencia;
    @Column(name = "hora_asistencia")
    @Temporal(TemporalType.TIME)
    private Date horaAsistencia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "calificacion_aprobacion")
    private BigDecimal calificacionAprobacion;
    @JoinColumn(name = "id_docente", referencedColumnName = "id_docente")
    @ManyToOne(optional = false)
    private Docente idDocente;
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id_estudiante")
    @ManyToOne(optional = false)
    private Estudiante idEstudiante;
    @JoinColumn(name = "id_oferta", referencedColumnName = "id_oferta")
    @ManyToOne(optional = false)
    private Ofertas idOferta;

    public Participaciones() {
    }

    public Participaciones(Integer idParticipacion) {
        this.idParticipacion = idParticipacion;
    }

    public Participaciones(Integer idParticipacion, BigDecimal calificacionAprobacion) {
        this.idParticipacion = idParticipacion;
        this.calificacionAprobacion = calificacionAprobacion;
    }

    public Integer getIdParticipacion() {
        return idParticipacion;
    }

    public void setIdParticipacion(Integer idParticipacion) {
        this.idParticipacion = idParticipacion;
    }

    public Date getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(Date fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }

    public Date getHoraAsistencia() {
        return horaAsistencia;
    }

    public void setHoraAsistencia(Date horaAsistencia) {
        this.horaAsistencia = horaAsistencia;
    }

    public BigDecimal getCalificacionAprobacion() {
        return calificacionAprobacion;
    }

    public void setCalificacionAprobacion(BigDecimal calificacionAprobacion) {
        this.calificacionAprobacion = calificacionAprobacion;
    }

    public Docente getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Docente idDocente) {
        this.idDocente = idDocente;
    }

    public Estudiante getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Estudiante idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Ofertas getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(Ofertas idOferta) {
        this.idOferta = idOferta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParticipacion != null ? idParticipacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Participaciones)) {
            return false;
        }
        Participaciones other = (Participaciones) object;
        if ((this.idParticipacion == null && other.idParticipacion != null) || (this.idParticipacion != null && !this.idParticipacion.equals(other.idParticipacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Participaciones[ idParticipacion=" + idParticipacion + " ]";
    }
    
}
