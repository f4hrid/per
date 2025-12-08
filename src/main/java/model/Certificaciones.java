/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author Fahrid
 */
@Entity
@Table(name = "certificaciones", catalog = "bd_gea", schema = "")
@NamedQueries({
    @NamedQuery(name = "Certificaciones.findAll", query = "SELECT c FROM Certificaciones c"),
    @NamedQuery(name = "Certificaciones.findByIdCertificado", query = "SELECT c FROM Certificaciones c WHERE c.idCertificado = :idCertificado"),
    @NamedQuery(name = "Certificaciones.findByFechaCertificado", query = "SELECT c FROM Certificaciones c WHERE c.fechaCertificado = :fechaCertificado")})
public class Certificaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_certificado", nullable = false)
    private Integer idCertificado;
    @Basic(optional = false)
    @Column(name = "fecha_certificado", nullable = false, length = 10)
    private String fechaCertificado;
    @JoinColumn(name = "id_docente", referencedColumnName = "id_docente", nullable = false)
    @ManyToOne(optional = false)
    private Docente idDocente;
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id_estudiante", nullable = false)
    @ManyToOne(optional = false)
    private Estudiante idEstudiante;
    @JoinColumn(name = "id_oferta", referencedColumnName = "id_oferta", nullable = false)
    @ManyToOne(optional = false)
    private Ofertas idOferta;

    public Certificaciones() {
    }

    public Certificaciones(Integer idCertificado) {
        this.idCertificado = idCertificado;
    }

    public Certificaciones(Integer idCertificado, String fechaCertificado) {
        this.idCertificado = idCertificado;
        this.fechaCertificado = fechaCertificado;
    }

    public Integer getIdCertificado() {
        return idCertificado;
    }

    public void setIdCertificado(Integer idCertificado) {
        this.idCertificado = idCertificado;
    }

    public String getFechaCertificado() {
        return fechaCertificado;
    }

    public void setFechaCertificado(String fechaCertificado) {
        this.fechaCertificado = fechaCertificado;
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
        hash += (idCertificado != null ? idCertificado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Certificaciones)) {
            return false;
        }
        Certificaciones other = (Certificaciones) object;
        if ((this.idCertificado == null && other.idCertificado != null) || (this.idCertificado != null && !this.idCertificado.equals(other.idCertificado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Certificaciones[ idCertificado=" + idCertificado + " ]";
    }
    
}
