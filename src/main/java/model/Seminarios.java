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
@Table(name = "seminarios")
@NamedQueries({
    @NamedQuery(name = "Seminarios.findAll", query = "SELECT s FROM Seminarios s"),
    @NamedQuery(name = "Seminarios.findByIdSeminario", query = "SELECT s FROM Seminarios s WHERE s.idSeminario = :idSeminario"),
    @NamedQuery(name = "Seminarios.findByNombreSeminario", query = "SELECT s FROM Seminarios s WHERE s.nombreSeminario = :nombreSeminario")})
public class Seminarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_seminario")
    private Integer idSeminario;
    @Basic(optional = false)
    @Column(name = "nombre_seminario")
    private String nombreSeminario;
    @JoinColumn(name = "id_oferta", referencedColumnName = "id_oferta")
    @ManyToOne(optional = false)
    private Ofertas idOferta;

    public Seminarios() {
    }

    public Seminarios(Integer idSeminario) {
        this.idSeminario = idSeminario;
    }

    public Seminarios(Integer idSeminario, String nombreSeminario) {
        this.idSeminario = idSeminario;
        this.nombreSeminario = nombreSeminario;
    }

    public Integer getIdSeminario() {
        return idSeminario;
    }

    public void setIdSeminario(Integer idSeminario) {
        this.idSeminario = idSeminario;
    }

    public String getNombreSeminario() {
        return nombreSeminario;
    }

    public void setNombreSeminario(String nombreSeminario) {
        this.nombreSeminario = nombreSeminario;
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
        hash += (idSeminario != null ? idSeminario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seminarios)) {
            return false;
        }
        Seminarios other = (Seminarios) object;
        if ((this.idSeminario == null && other.idSeminario != null) || (this.idSeminario != null && !this.idSeminario.equals(other.idSeminario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Seminarios[ idSeminario=" + idSeminario + " ]";
    }
    
}
