/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Fahrid
 */
@Entity
@Table(name = "ofertas", catalog = "bd_gea", schema = "")
@NamedQueries({
    @NamedQuery(name = "Ofertas.findAll", query = "SELECT o FROM Ofertas o"),
    @NamedQuery(name = "Ofertas.findByIdOferta", query = "SELECT o FROM Ofertas o WHERE o.idOferta = :idOferta"),
    @NamedQuery(name = "Ofertas.findByCodigoOferta", query = "SELECT o FROM Ofertas o WHERE o.codigoOferta = :codigoOferta"),
    @NamedQuery(name = "Ofertas.findByActivaOferta", query = "SELECT o FROM Ofertas o WHERE o.activaOferta = :activaOferta"),
    @NamedQuery(name = "Ofertas.findByTipoOferta", query = "SELECT o FROM Ofertas o WHERE o.tipoOferta = :tipoOferta"),
    @NamedQuery(name = "Ofertas.findByCupoOferta", query = "SELECT o FROM Ofertas o WHERE o.cupoOferta = :cupoOferta"),
    @NamedQuery(name = "Ofertas.findByDuracionOferta", query = "SELECT o FROM Ofertas o WHERE o.duracionOferta = :duracionOferta")})
public class Ofertas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_oferta", nullable = false)
    private Integer idOferta;
    @Basic(optional = false)
    @Column(name = "codigo_oferta", nullable = false, length = 15)
    private String codigoOferta;
    @Basic(optional = false)
    @Column(name = "activa_oferta", nullable = false, length = 8)
    private String activaOferta;
    @Column(name = "tipo_oferta", length = 10)
    private String tipoOferta;
    @Column(name = "cupo_oferta")
    private Integer cupoOferta;
    @Column(name = "duracion_oferta", length = 20)
    private String duracionOferta;
    @Lob
    @Column(name = "descripcion_oferta", length = 65535)
    private String descripcionOferta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOferta")
    private Collection<Participaciones> participacionesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOferta")
    private Collection<Cursos> cursosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOferta")
    private Collection<Seminarios> seminariosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ofertas")
    private Collection<Inscripcion> inscripcionCollection;
    @JoinColumn(name = "id_docente", referencedColumnName = "id_docente")
    @ManyToOne
    private Docente idDocente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOferta")
    private Collection<Certificaciones> certificacionesCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "ofertas")
    private Talleres talleres;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOferta")
    private Collection<Diplomados> diplomadosCollection;

    public Ofertas() {
    }

    public Ofertas(Integer idOferta) {
        this.idOferta = idOferta;
    }

    public Ofertas(Integer idOferta, String codigoOferta, String activaOferta) {
        this.idOferta = idOferta;
        this.codigoOferta = codigoOferta;
        this.activaOferta = activaOferta;
    }

    public Integer getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(Integer idOferta) {
        this.idOferta = idOferta;
    }

    public String getCodigoOferta() {
        return codigoOferta;
    }

    public void setCodigoOferta(String codigoOferta) {
        this.codigoOferta = codigoOferta;
    }

    public String getActivaOferta() {
        return activaOferta;
    }

    public void setActivaOferta(String activaOferta) {
        this.activaOferta = activaOferta;
    }

    public String getTipoOferta() {
        return tipoOferta;
    }

    public void setTipoOferta(String tipoOferta) {
        this.tipoOferta = tipoOferta;
    }

    public Integer getCupoOferta() {
        return cupoOferta;
    }

    public void setCupoOferta(Integer cupoOferta) {
        this.cupoOferta = cupoOferta;
    }

    public String getDuracionOferta() {
        return duracionOferta;
    }

    public void setDuracionOferta(String duracionOferta) {
        this.duracionOferta = duracionOferta;
    }

    public String getDescripcionOferta() {
        return descripcionOferta;
    }

    public void setDescripcionOferta(String descripcionOferta) {
        this.descripcionOferta = descripcionOferta;
    }

    public Collection<Participaciones> getParticipacionesCollection() {
        return participacionesCollection;
    }

    public void setParticipacionesCollection(Collection<Participaciones> participacionesCollection) {
        this.participacionesCollection = participacionesCollection;
    }

    public Collection<Cursos> getCursosCollection() {
        return cursosCollection;
    }

    public void setCursosCollection(Collection<Cursos> cursosCollection) {
        this.cursosCollection = cursosCollection;
    }

    public Collection<Seminarios> getSeminariosCollection() {
        return seminariosCollection;
    }

    public void setSeminariosCollection(Collection<Seminarios> seminariosCollection) {
        this.seminariosCollection = seminariosCollection;
    }

    public Collection<Inscripcion> getInscripcionCollection() {
        return inscripcionCollection;
    }

    public void setInscripcionCollection(Collection<Inscripcion> inscripcionCollection) {
        this.inscripcionCollection = inscripcionCollection;
    }

    public Docente getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Docente idDocente) {
        this.idDocente = idDocente;
    }

    public Collection<Certificaciones> getCertificacionesCollection() {
        return certificacionesCollection;
    }

    public void setCertificacionesCollection(Collection<Certificaciones> certificacionesCollection) {
        this.certificacionesCollection = certificacionesCollection;
    }

    public Talleres getTalleres() {
        return talleres;
    }

    public void setTalleres(Talleres talleres) {
        this.talleres = talleres;
    }

    public Collection<Diplomados> getDiplomadosCollection() {
        return diplomadosCollection;
    }

    public void setDiplomadosCollection(Collection<Diplomados> diplomadosCollection) {
        this.diplomadosCollection = diplomadosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOferta != null ? idOferta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ofertas)) {
            return false;
        }
        Ofertas other = (Ofertas) object;
        if ((this.idOferta == null && other.idOferta != null) || (this.idOferta != null && !this.idOferta.equals(other.idOferta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Ofertas[ idOferta=" + idOferta + " ]";
    }
    
}
