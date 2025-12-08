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
import model.Estudiante;
import model.Inscripcion;
import model.InscripcionPK;
import model.Ofertas;

/**
 *
 * @author Fahrid
 */
public class InscripcionJpaController implements Serializable {

    public InscripcionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Inscripcion inscripcion) throws PreexistingEntityException, Exception {
        if (inscripcion.getInscripcionPK() == null) {
            inscripcion.setInscripcionPK(new InscripcionPK());
        }
        inscripcion.getInscripcionPK().setIdEstudiante(inscripcion.getEstudiante().getIdEstudiante());
        inscripcion.getInscripcionPK().setIdOferta(inscripcion.getOfertas().getIdOferta());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiante estudiante = inscripcion.getEstudiante();
            if (estudiante != null) {
                estudiante = em.getReference(estudiante.getClass(), estudiante.getIdEstudiante());
                inscripcion.setEstudiante(estudiante);
            }
            Ofertas ofertas = inscripcion.getOfertas();
            if (ofertas != null) {
                ofertas = em.getReference(ofertas.getClass(), ofertas.getIdOferta());
                inscripcion.setOfertas(ofertas);
            }
            em.persist(inscripcion);
            if (estudiante != null) {
                estudiante.getInscripcionCollection().add(inscripcion);
                estudiante = em.merge(estudiante);
            }
            if (ofertas != null) {
                ofertas.getInscripcionCollection().add(inscripcion);
                ofertas = em.merge(ofertas);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInscripcion(inscripcion.getInscripcionPK()) != null) {
                throw new PreexistingEntityException("Inscripcion " + inscripcion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Inscripcion inscripcion) throws NonexistentEntityException, Exception {
        inscripcion.getInscripcionPK().setIdEstudiante(inscripcion.getEstudiante().getIdEstudiante());
        inscripcion.getInscripcionPK().setIdOferta(inscripcion.getOfertas().getIdOferta());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inscripcion persistentInscripcion = em.find(Inscripcion.class, inscripcion.getInscripcionPK());
            Estudiante estudianteOld = persistentInscripcion.getEstudiante();
            Estudiante estudianteNew = inscripcion.getEstudiante();
            Ofertas ofertasOld = persistentInscripcion.getOfertas();
            Ofertas ofertasNew = inscripcion.getOfertas();
            if (estudianteNew != null) {
                estudianteNew = em.getReference(estudianteNew.getClass(), estudianteNew.getIdEstudiante());
                inscripcion.setEstudiante(estudianteNew);
            }
            if (ofertasNew != null) {
                ofertasNew = em.getReference(ofertasNew.getClass(), ofertasNew.getIdOferta());
                inscripcion.setOfertas(ofertasNew);
            }
            inscripcion = em.merge(inscripcion);
            if (estudianteOld != null && !estudianteOld.equals(estudianteNew)) {
                estudianteOld.getInscripcionCollection().remove(inscripcion);
                estudianteOld = em.merge(estudianteOld);
            }
            if (estudianteNew != null && !estudianteNew.equals(estudianteOld)) {
                estudianteNew.getInscripcionCollection().add(inscripcion);
                estudianteNew = em.merge(estudianteNew);
            }
            if (ofertasOld != null && !ofertasOld.equals(ofertasNew)) {
                ofertasOld.getInscripcionCollection().remove(inscripcion);
                ofertasOld = em.merge(ofertasOld);
            }
            if (ofertasNew != null && !ofertasNew.equals(ofertasOld)) {
                ofertasNew.getInscripcionCollection().add(inscripcion);
                ofertasNew = em.merge(ofertasNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                InscripcionPK id = inscripcion.getInscripcionPK();
                if (findInscripcion(id) == null) {
                    throw new NonexistentEntityException("The inscripcion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(InscripcionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inscripcion inscripcion;
            try {
                inscripcion = em.getReference(Inscripcion.class, id);
                inscripcion.getInscripcionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inscripcion with id " + id + " no longer exists.", enfe);
            }
            Estudiante estudiante = inscripcion.getEstudiante();
            if (estudiante != null) {
                estudiante.getInscripcionCollection().remove(inscripcion);
                estudiante = em.merge(estudiante);
            }
            Ofertas ofertas = inscripcion.getOfertas();
            if (ofertas != null) {
                ofertas.getInscripcionCollection().remove(inscripcion);
                ofertas = em.merge(ofertas);
            }
            em.remove(inscripcion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Inscripcion> findInscripcionEntities() {
        return findInscripcionEntities(true, -1, -1);
    }

    public List<Inscripcion> findInscripcionEntities(int maxResults, int firstResult) {
        return findInscripcionEntities(false, maxResults, firstResult);
    }

    private List<Inscripcion> findInscripcionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Inscripcion.class));
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

    public Inscripcion findInscripcion(InscripcionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Inscripcion.class, id);
        } finally {
            em.close();
        }
    }

    public int getInscripcionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Inscripcion> rt = cq.from(Inscripcion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
