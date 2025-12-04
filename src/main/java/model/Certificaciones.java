/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Fahrid
 */
@Entity
@Table(name = "certificaciones")
@NamedQueries({
    @NamedQuery(name = "Certificaciones.findAll", query = "SELECT c FROM Certificaciones c"),
    @NamedQuery(name = "Certificaciones.findByIdCertificado", query = "SELECT c FROM Certificaciones c WHERE c.idCertificado = :idCertificado"),
    @NamedQuery(name = "Certificaciones.findByFechaCertificado", query = "SELECT c FROM Certificaciones c WHERE c.fechaCertificado = :fechaCertificado")})
public class Certificaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_certificado")
    private Integer idCertificado;
    @Basic(optional = false)
    @Column(name = "fecha_certificado")
    private String fechaCertificado;
    @JoinColumn(name = "id_docente", referencedColumnName = "id_docente")
    @ManyToOne(optional = false)
    private Docente idDocente;
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id_estudiante")
    @ManyToOne(optional = false)
    private Estudiante idEstudiante;
    @JoinColumn(name = "id_oferta", referencedColumnName = "id_oferta")
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
