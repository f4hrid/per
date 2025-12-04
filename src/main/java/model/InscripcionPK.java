/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Fahrid
 */
@Embeddable
public class InscripcionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_estudiante")
    private int idEstudiante;
    @Basic(optional = false)
    @Column(name = "id_oferta")
    private int idOferta;

    public InscripcionPK() {
    }

    public InscripcionPK(int idEstudiante, int idOferta) {
        this.idEstudiante = idEstudiante;
        this.idOferta = idOferta;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEstudiante;
        hash += (int) idOferta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InscripcionPK)) {
            return false;
        }
        InscripcionPK other = (InscripcionPK) object;
        if (this.idEstudiante != other.idEstudiante) {
            return false;
        }
        if (this.idOferta != other.idOferta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.InscripcionPK[ idEstudiante=" + idEstudiante + ", idOferta=" + idOferta + " ]";
    }
    
}
