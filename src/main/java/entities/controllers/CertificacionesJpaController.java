/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import model.Certificaciones;
import model.Docente;
import model.Estudiante;
import model.Ofertas;

/**
 *
 * @author Fahrid
 */
public class CertificacionesJpaController implements Serializable {

    public CertificacionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Certificaciones certificaciones) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docente idDocente = certificaciones.getIdDocente();
            if (idDocente != null) {
                idDocente = em.getReference(idDocente.getClass(), idDocente.getIdDocente());
                certificaciones.setIdDocente(idDocente);
            }
            Estudiante idEstudiante = certificaciones.getIdEstudiante();
            if (idEstudiante != null) {
                idEstudiante = em.getReference(idEstudiante.getClass(), idEstudiante.getIdEstudiante());
                certificaciones.setIdEstudiante(idEstudiante);
            }
            Ofertas idOferta = certificaciones.getIdOferta();
            if (idOferta != null) {
                idOferta = em.getReference(idOferta.getClass(), idOferta.getIdOferta());
                certificaciones.setIdOferta(idOferta);
            }
            em.persist(certificaciones);
            if (idDocente != null) {
                idDocente.getCertificacionesCollection().add(certificaciones);
                idDocente = em.merge(idDocente);
            }
            if (idEstudiante != null) {
                idEstudiante.getCertificacionesCollection().add(certificaciones);
                idEstudiante = em.merge(idEstudiante);
            }
            if (idOferta != null) {
                idOferta.getCertificacionesCollection().add(certificaciones);
                idOferta = em.merge(idOferta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCertificaciones(certificaciones.getIdCertificado()) != null) {
                throw new PreexistingEntityException("Certificaciones " + certificaciones + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Certificaciones certificaciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Certificaciones persistentCertificaciones = em.find(Certificaciones.class, certificaciones.getIdCertificado());
            Docente idDocenteOld = persistentCertificaciones.getIdDocente();
            Docente idDocenteNew = certificaciones.getIdDocente();
            Estudiante idEstudianteOld = persistentCertificaciones.getIdEstudiante();
            Estudiante idEstudianteNew = certificaciones.getIdEstudiante();
            Ofertas idOfertaOld = persistentCertificaciones.getIdOferta();
            Ofertas idOfertaNew = certificaciones.getIdOferta();
            if (idDocenteNew != null) {
                idDocenteNew = em.getReference(idDocenteNew.getClass(), idDocenteNew.getIdDocente());
                certificaciones.setIdDocente(idDocenteNew);
            }
            if (idEstudianteNew != null) {
                idEstudianteNew = em.getReference(idEstudianteNew.getClass(), idEstudianteNew.getIdEstudiante());
                certificaciones.setIdEstudiante(idEstudianteNew);
            }
            if (idOfertaNew != null) {
                idOfertaNew = em.getReference(idOfertaNew.getClass(), idOfertaNew.getIdOferta());
                certificaciones.setIdOferta(idOfertaNew);
            }
            certificaciones = em.merge(certificaciones);
            if (idDocenteOld != null && !idDocenteOld.equals(idDocenteNew)) {
                idDocenteOld.getCertificacionesCollection().remove(certificaciones);
                idDocenteOld = em.merge(idDocenteOld);
            }
            if (idDocenteNew != null && !idDocenteNew.equals(idDocenteOld)) {
                idDocenteNew.getCertificacionesCollection().add(certificaciones);
                idDocenteNew = em.merge(idDocenteNew);
            }
            if (idEstudianteOld != null && !idEstudianteOld.equals(idEstudianteNew)) {
                idEstudianteOld.getCertificacionesCollection().remove(certificaciones);
                idEstudianteOld = em.merge(idEstudianteOld);
            }
            if (idEstudianteNew != null && !idEstudianteNew.equals(idEstudianteOld)) {
                idEstudianteNew.getCertificacionesCollection().add(certificaciones);
                idEstudianteNew = em.merge(idEstudianteNew);
            }
            if (idOfertaOld != null && !idOfertaOld.equals(idOfertaNew)) {
                idOfertaOld.getCertificacionesCollection().remove(certificaciones);
                idOfertaOld = em.merge(idOfertaOld);
            }
            if (idOfertaNew != null && !idOfertaNew.equals(idOfertaOld)) {
                idOfertaNew.getCertificacionesCollection().add(certificaciones);
                idOfertaNew = em.merge(idOfertaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = certificaciones.getIdCertificado();
                if (findCertificaciones(id) == null) {
                    throw new NonexistentEntityException("The certificaciones with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Certificaciones certificaciones;
            try {
                certificaciones = em.getReference(Certificaciones.class, id);
                certificaciones.getIdCertificado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The certificaciones with id " + id + " no longer exists.", enfe);
            }
            Docente idDocente = certificaciones.getIdDocente();
            if (idDocente != null) {
                idDocente.getCertificacionesCollection().remove(certificaciones);
                idDocente = em.merge(idDocente);
            }
            Estudiante idEstudiante = certificaciones.getIdEstudiante();
            if (idEstudiante != null) {
                idEstudiante.getCertificacionesCollection().remove(certificaciones);
                idEstudiante = em.merge(idEstudiante);
            }
            Ofertas idOferta = certificaciones.getIdOferta();
            if (idOferta != null) {
                idOferta.getCertificacionesCollection().remove(certificaciones);
                idOferta = em.merge(idOferta);
            }
            em.remove(certificaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Certificaciones> findCertificacionesEntities() {
        return findCertificacionesEntities(true, -1, -1);
    }

    public List<Certificaciones> findCertificacionesEntities(int maxResults, int firstResult) {
        return findCertificacionesEntities(false, maxResults, firstResult);
    }

    private List<Certificaciones> findCertificacionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Certificaciones.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Certificaciones findCertificaciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Certificaciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getCertificacionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Certificaciones> rt = cq.from(Certificaciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
