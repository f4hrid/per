/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Collection;
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

/**
 *
 * @author Fahrid
 */
@Entity
@Table(name = "docente")
@NamedQueries({
    @NamedQuery(name = "Docente.findAll", query = "SELECT d FROM Docente d"),
    @NamedQuery(name = "Docente.findByIdDocente", query = "SELECT d FROM Docente d WHERE d.idDocente = :idDocente"),
    @NamedQuery(name = "Docente.findByNombreDocente", query = "SELECT d FROM Docente d WHERE d.nombreDocente = :nombreDocente")})
public class Docente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_docente")
    private Integer idDocente;
    @Basic(optional = false)
    @Column(name = "nombre_docente")
    private String nombreDocente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDocente")
    private Collection<Participaciones> participacionesCollection;
    @OneToMany(mappedBy = "idDocente")
    private Collection<Ofertas> ofertasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDocente")
    private Collection<Certificaciones> certificacionesCollection;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @OneToOne(optional = false)
    private Usuario idUsuario;

    public Docente() {
    }

    public Docente(Integer idDocente) {
        this.idDocente = idDocente;
    }

    public Docente(Integer idDocente, String nombreDocente) {
        this.idDocente = idDocente;
        this.nombreDocente = nombreDocente;
    }

    public Integer getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Integer idDocente) {
        this.idDocente = idDocente;
    }

    public String getNombreDocente() {
        return nombreDocente;
    }

    public void setNombreDocente(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    public Collection<Participaciones> getParticipacionesCollection() {
        return participacionesCollection;
    }

    public void setParticipacionesCollection(Collection<Participaciones> participacionesCollection) {
        this.participacionesCollection = participacionesCollection;
    }

    public Collection<Ofertas> getOfertasCollection() {
        return ofertasCollection;
    }

    public void setOfertasCollection(Collection<Ofertas> ofertasCollection) {
        this.ofertasCollection = ofertasCollection;
    }

    public Collection<Certificaciones> getCertificacionesCollection() {
        return certificacionesCollection;
    }

    public void setCertificacionesCollection(Collection<Certificaciones> certificacionesCollection) {
        this.certificacionesCollection = certificacionesCollection;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocente != null ? idDocente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Docente)) {
            return false;
        }
        Docente other = (Docente) object;
        if ((this.idDocente == null && other.idDocente != null) || (this.idDocente != null && !this.idDocente.equals(other.idDocente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Docente[ idDocente=" + idDocente + " ]";
    }
    
}
