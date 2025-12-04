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
@Table(name = "diplomados")
@NamedQueries({
    @NamedQuery(name = "Diplomados.findAll", query = "SELECT d FROM Diplomados d"),
    @NamedQuery(name = "Diplomados.findByIdDiplomado", query = "SELECT d FROM Diplomados d WHERE d.idDiplomado = :idDiplomado"),
    @NamedQuery(name = "Diplomados.findByTituloDiplomado", query = "SELECT d FROM Diplomados d WHERE d.tituloDiplomado = :tituloDiplomado")})
public class Diplomados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_diplomado")
    private Integer idDiplomado;
    @Basic(optional = false)
    @Column(name = "titulo_diplomado")
    private String tituloDiplomado;
    @JoinColumn(name = "id_oferta", referencedColumnName = "id_oferta")
    @ManyToOne(optional = false)
    private Ofertas idOferta;

    public Diplomados() {
    }

    public Diplomados(Integer idDiplomado) {
        this.idDiplomado = idDiplomado;
    }

    public Diplomados(Integer idDiplomado, String tituloDiplomado) {
        this.idDiplomado = idDiplomado;
        this.tituloDiplomado = tituloDiplomado;
    }

    public Integer getIdDiplomado() {
        return idDiplomado;
    }

    public void setIdDiplomado(Integer idDiplomado) {
        this.idDiplomado = idDiplomado;
    }

    public String getTituloDiplomado() {
        return tituloDiplomado;
    }

    public void setTituloDiplomado(String tituloDiplomado) {
        this.tituloDiplomado = tituloDiplomado;
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
        hash += (idDiplomado != null ? idDiplomado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diplomados)) {
            return false;
        }
        Diplomados other = (Diplomados) object;
        if ((this.idDiplomado == null && other.idDiplomado != null) || (this.idDiplomado != null && !this.idDiplomado.equals(other.idDiplomado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Diplomados[ idDiplomado=" + idDiplomado + " ]";
    }
    
}
