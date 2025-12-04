/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Fahrid
 */
@Entity
@Table(name = "estudiante")
@NamedQueries({
    @NamedQuery(name = "Estudiante.findAll", query = "SELECT e FROM Estudiante e"),
    @NamedQuery(name = "Estudiante.findByIdEstudiante", query = "SELECT e FROM Estudiante e WHERE e.idEstudiante = :idEstudiante"),
    @NamedQuery(name = "Estudiante.findByNombreEstudiante", query = "SELECT e FROM Estudiante e WHERE e.nombreEstudiante = :nombreEstudiante"),
    @NamedQuery(name = "Estudiante.findByDniEstudiante", query = "SELECT e FROM Estudiante e WHERE e.dniEstudiante = :dniEstudiante"),
    @NamedQuery(name = "Estudiante.findByTelefonoEstudiante", query = "SELECT e FROM Estudiante e WHERE e.telefonoEstudiante = :telefonoEstudiante"),
    @NamedQuery(name = "Estudiante.findByEmailEstudiante", query = "SELECT e FROM Estudiante e WHERE e.emailEstudiante = :emailEstudiante"),
    @NamedQuery(name = "Estudiante.findByFechaNacimientoEstudiante", query = "SELECT e FROM Estudiante e WHERE e.fechaNacimientoEstudiante = :fechaNacimientoEstudiante"),
    @NamedQuery(name = "Estudiante.findByDireccionEstudiante", query = "SELECT e FROM Estudiante e WHERE e.direccionEstudiante = :direccionEstudiante")})
public class Estudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estudiante")
    private Integer idEstudiante;
    @Basic(optional = false)
    @Column(name = "nombre_estudiante")
    private String nombreEstudiante;
    @Basic(optional = false)
    @Column(name = "dni_estudiante")
    private String dniEstudiante;
    @Column(name = "telefono_estudiante")
    private String telefonoEstudiante;
    @Basic(optional = false)
    @Column(name = "email_estudiante")
    private String emailEstudiante;
    @Basic(optional = false)
    @Column(name = "fecha_nacimiento_estudiante")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimientoEstudiante;
    @Basic(optional = false)
    @Column(name = "direccion_estudiante")
    private String direccionEstudiante;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @OneToOne(optional = false)
    private Usuario idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstudiante")
    private Collection<Participaciones> participacionesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiante")
    private Collection<Inscripcion> inscripcionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstudiante")
    private Collection<Certificaciones> certificacionesCollection;

    public Estudiante() {
    }

    public Estudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Estudiante(Integer idEstudiante, String nombreEstudiante, String dniEstudiante, String emailEstudiante, Date fechaNacimientoEstudiante, String direccionEstudiante) {
        this.idEstudiante = idEstudiante;
        this.nombreEstudiante = nombreEstudiante;
        this.dniEstudiante = dniEstudiante;
        this.emailEstudiante = emailEstudiante;
        this.fechaNacimientoEstudiante = fechaNacimientoEstudiante;
        this.direccionEstudiante = direccionEstudiante;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getDniEstudiante() {
        return dniEstudiante;
    }

    public void setDniEstudiante(String dniEstudiante) {
        this.dniEstudiante = dniEstudiante;
    }

    public String getTelefonoEstudiante() {
        return telefonoEstudiante;
    }

    public void setTelefonoEstudiante(String telefonoEstudiante) {
        this.telefonoEstudiante = telefonoEstudiante;
    }

    public String getEmailEstudiante() {
        return emailEstudiante;
    }

    public void setEmailEstudiante(String emailEstudiante) {
        this.emailEstudiante = emailEstudiante;
    }

    public Date getFechaNacimientoEstudiante() {
        return fechaNacimientoEstudiante;
    }

    public void setFechaNacimientoEstudiante(Date fechaNacimientoEstudiante) {
        this.fechaNacimientoEstudiante = fechaNacimientoEstudiante;
    }

    public String getDireccionEstudiante() {
        return direccionEstudiante;
    }

    public void setDireccionEstudiante(String direccionEstudiante) {
        this.direccionEstudiante = direccionEstudiante;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Collection<Participaciones> getParticipacionesCollection() {
        return participacionesCollection;
    }

    public void setParticipacionesCollection(Collection<Participaciones> participacionesCollection) {
        this.participacionesCollection = participacionesCollection;
    }

    public Collection<Inscripcion> getInscripcionCollection() {
        return inscripcionCollection;
    }

    public void setInscripcionCollection(Collection<Inscripcion> inscripcionCollection) {
        this.inscripcionCollection = inscripcionCollection;
    }

    public Collection<Certificaciones> getCertificacionesCollection() {
        return certificacionesCollection;
    }

    public void setCertificacionesCollection(Collection<Certificaciones> certificacionesCollection) {
        this.certificacionesCollection = certificacionesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstudiante != null ? idEstudiante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiante)) {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.idEstudiante == null && other.idEstudiante != null) || (this.idEstudiante != null && !this.idEstudiante.equals(other.idEstudiante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Estudiante[ idEstudiante=" + idEstudiante + " ]";
    }
    
}
