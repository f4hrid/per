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
@Table(name = "diplomados", catalog = "bd_gea", schema = "")
@NamedQueries({
    @NamedQuery(name = "Diplomados.findAll", query = "SELECT d FROM Diplomados d"),
    @NamedQuery(name = "Diplomados.findByIdDiplomado", query = "SELECT d FROM Diplomados d WHERE d.idDiplomado = :idDiplomado"),
    @NamedQuery(name = "Diplomados.findByTituloDiplomado", query = "SELECT d FROM Diplomados d WHERE d.tituloDiplomado = :tituloDiplomado")})
public class Diplomados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_diplomado", nullable = false)
    private Integer idDiplomado;
    @Basic(optional = false)
    @Column(name = "titulo_diplomado", nullable = false, length = 100)
    private String tituloDiplomado;
    @JoinColumn(name = "id_oferta", referencedColumnName = "id_oferta", nullable = false)
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
