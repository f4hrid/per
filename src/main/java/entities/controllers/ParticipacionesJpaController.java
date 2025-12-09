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
import model.Docente;
import model.Estudiante;
import model.Ofertas;
import model.Participaciones;

/**
 *
 * @author Fahrid
 */
public class ParticipacionesJpaController implements Serializable {

    public ParticipacionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Participaciones participaciones) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docente idDocente = participaciones.getIdDocente();
            if (idDocente != null) {
                idDocente = em.getReference(idDocente.getClass(), idDocente.getIdDocente());
                participaciones.setIdDocente(idDocente);
            }
            Estudiante idEstudiante = participaciones.getIdEstudiante();
            if (idEstudiante != null) {
                idEstudiante = em.getReference(idEstudiante.getClass(), idEstudiante.getIdEstudiante());
                participaciones.setIdEstudiante(idEstudiante);
            }
            Ofertas idOferta = participaciones.getIdOferta();
            if (idOferta != null) {
                idOferta = em.getReference(idOferta.getClass(), idOferta.getIdOferta());
                participaciones.setIdOferta(idOferta);
            }
            em.persist(participaciones);
            if (idDocente != null) {
                idDocente.getParticipacionesCollection().add(participaciones);
                idDocente = em.merge(idDocente);
            }
            if (idEstudiante != null) {
                idEstudiante.getParticipacionesCollection().add(participaciones);
                idEstudiante = em.merge(idEstudiante);
            }
            if (idOferta != null) {
                idOferta.getParticipacionesCollection().add(participaciones);
                idOferta = em.merge(idOferta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findParticipaciones(participaciones.getIdParticipacion()) != null) {
                throw new PreexistingEntityException("Participaciones " + participaciones + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Participaciones participaciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Participaciones persistentParticipaciones = em.find(Participaciones.class, participaciones.getIdParticipacion());
            Docente idDocenteOld = persistentParticipaciones.getIdDocente();
            Docente idDocenteNew = participaciones.getIdDocente();
            Estudiante idEstudianteOld = persistentParticipaciones.getIdEstudiante();
            Estudiante idEstudianteNew = participaciones.getIdEstudiante();
            Ofertas idOfertaOld = persistentParticipaciones.getIdOferta();
            Ofertas idOfertaNew = participaciones.getIdOferta();
            if (idDocenteNew != null) {
                idDocenteNew = em.getReference(idDocenteNew.getClass(), idDocenteNew.getIdDocente());
                participaciones.setIdDocente(idDocenteNew);
            }
            if (idEstudianteNew != null) {
                idEstudianteNew = em.getReference(idEstudianteNew.getClass(), idEstudianteNew.getIdEstudiante());
                participaciones.setIdEstudiante(idEstudianteNew);
            }
            if (idOfertaNew != null) {
                idOfertaNew = em.getReference(idOfertaNew.getClass(), idOfertaNew.getIdOferta());
                participaciones.setIdOferta(idOfertaNew);
            }
            participaciones = em.merge(participaciones);
            if (idDocenteOld != null && !idDocenteOld.equals(idDocenteNew)) {
                idDocenteOld.getParticipacionesCollection().remove(participaciones);
                idDocenteOld = em.merge(idDocenteOld);
            }
            if (idDocenteNew != null && !idDocenteNew.equals(idDocenteOld)) {
                idDocenteNew.getParticipacionesCollection().add(participaciones);
                idDocenteNew = em.merge(idDocenteNew);
            }
            if (idEstudianteOld != null && !idEstudianteOld.equals(idEstudianteNew)) {
                idEstudianteOld.getParticipacionesCollection().remove(participaciones);
                idEstudianteOld = em.merge(idEstudianteOld);
            }
            if (idEstudianteNew != null && !idEstudianteNew.equals(idEstudianteOld)) {
                idEstudianteNew.getParticipacionesCollection().add(participaciones);
                idEstudianteNew = em.merge(idEstudianteNew);
            }
            if (idOfertaOld != null && !idOfertaOld.equals(idOfertaNew)) {
                idOfertaOld.getParticipacionesCollection().remove(participaciones);
                idOfertaOld = em.merge(idOfertaOld);
            }
            if (idOfertaNew != null && !idOfertaNew.equals(idOfertaOld)) {
                idOfertaNew.getParticipacionesCollection().add(participaciones);
                idOfertaNew = em.merge(idOfertaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = participaciones.getIdParticipacion();
                if (findParticipaciones(id) == null) {
                    throw new NonexistentEntityException("The participaciones with id " + id + " no longer exists.");
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
            Participaciones participaciones;
            try {
                participaciones = em.getReference(Participaciones.class, id);
                participaciones.getIdParticipacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The participaciones with id " + id + " no longer exists.", enfe);
            }
            Docente idDocente = participaciones.getIdDocente();
            if (idDocente != null) {
                idDocente.getParticipacionesCollection().remove(participaciones);
                idDocente = em.merge(idDocente);
            }
            Estudiante idEstudiante = participaciones.getIdEstudiante();
            if (idEstudiante != null) {
                idEstudiante.getParticipacionesCollection().remove(participaciones);
                idEstudiante = em.merge(idEstudiante);
            }
            Ofertas idOferta = participaciones.getIdOferta();
            if (idOferta != null) {
                idOferta.getParticipacionesCollection().remove(participaciones);
                idOferta = em.merge(idOferta);
            }
            em.remove(participaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Participaciones> findParticipacionesEntities() {
        return findParticipacionesEntities(true, -1, -1);
    }

    public List<Participaciones> findParticipacionesEntities(int maxResults, int firstResult) {
        return findParticipacionesEntities(false, maxResults, firstResult);
    }

    private List<Participaciones> findParticipacionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Participaciones.class));
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

    public Participaciones findParticipaciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Participaciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getParticipacionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Participaciones> rt = cq.from(Participaciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
