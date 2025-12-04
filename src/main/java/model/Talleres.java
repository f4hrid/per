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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Fahrid
 */
@Entity
@Table(name = "talleres")
@NamedQueries({
    @NamedQuery(name = "Talleres.findAll", query = "SELECT t FROM Talleres t"),
    @NamedQuery(name = "Talleres.findByIdTaller", query = "SELECT t FROM Talleres t WHERE t.idTaller = :idTaller"),
    @NamedQuery(name = "Talleres.findByIdOferta", query = "SELECT t FROM Talleres t WHERE t.idOferta = :idOferta"),
    @NamedQuery(name = "Talleres.findByNombreTaller", query = "SELECT t FROM Talleres t WHERE t.nombreTaller = :nombreTaller")})
public class Talleres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "id_taller")
    private int idTaller;
    @Id
    @Basic(optional = false)
    @Column(name = "id_oferta")
    private Integer idOferta;
    @Basic(optional = false)
    @Column(name = "nombre_taller")
    private String nombreTaller;
    @JoinColumn(name = "id_oferta", referencedColumnName = "id_oferta", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Ofertas ofertas;

    public Talleres() {
    }

    public Talleres(Integer idOferta) {
        this.idOferta = idOferta;
    }

    public Talleres(Integer idOferta, int idTaller, String nombreTaller) {
        this.idOferta = idOferta;
        this.idTaller = idTaller;
        this.nombreTaller = nombreTaller;
    }

    public int getIdTaller() {
        return idTaller;
    }

    public void setIdTaller(int idTaller) {
        this.idTaller = idTaller;
    }

    public Integer getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(Integer idOferta) {
        this.idOferta = idOferta;
    }

    public String getNombreTaller() {
        return nombreTaller;
    }

    public void setNombreTaller(String nombreTaller) {
        this.nombreTaller = nombreTaller;
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
        hash += (idOferta != null ? idOferta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Talleres)) {
            return false;
        }
        Talleres other = (Talleres) object;
        if ((this.idOferta == null && other.idOferta != null) || (this.idOferta != null && !this.idOferta.equals(other.idOferta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Talleres[ idOferta=" + idOferta + " ]";
    }
    
}
