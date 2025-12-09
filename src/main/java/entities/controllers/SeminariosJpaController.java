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
import model.Ofertas;
import model.Seminarios;

/**
 *
 * @author Fahrid
 */
public class SeminariosJpaController implements Serializable {

    public SeminariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Seminarios seminarios) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ofertas idOferta = seminarios.getIdOferta();
            if (idOferta != null) {
                idOferta = em.getReference(idOferta.getClass(), idOferta.getIdOferta());
                seminarios.setIdOferta(idOferta);
            }
            em.persist(seminarios);
            if (idOferta != null) {
                idOferta.getSeminariosCollection().add(seminarios);
                idOferta = em.merge(idOferta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSeminarios(seminarios.getIdSeminario()) != null) {
                throw new PreexistingEntityException("Seminarios " + seminarios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Seminarios seminarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Seminarios persistentSeminarios = em.find(Seminarios.class, seminarios.getIdSeminario());
            Ofertas idOfertaOld = persistentSeminarios.getIdOferta();
            Ofertas idOfertaNew = seminarios.getIdOferta();
            if (idOfertaNew != null) {
                idOfertaNew = em.getReference(idOfertaNew.getClass(), idOfertaNew.getIdOferta());
                seminarios.setIdOferta(idOfertaNew);
            }
            seminarios = em.merge(seminarios);
            if (idOfertaOld != null && !idOfertaOld.equals(idOfertaNew)) {
                idOfertaOld.getSeminariosCollection().remove(seminarios);
                idOfertaOld = em.merge(idOfertaOld);
            }
            if (idOfertaNew != null && !idOfertaNew.equals(idOfertaOld)) {
                idOfertaNew.getSeminariosCollection().add(seminarios);
                idOfertaNew = em.merge(idOfertaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = seminarios.getIdSeminario();
                if (findSeminarios(id) == null) {
                    throw new NonexistentEntityException("The seminarios with id " + id + " no longer exists.");
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
            Seminarios seminarios;
            try {
                seminarios = em.getReference(Seminarios.class, id);
                seminarios.getIdSeminario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seminarios with id " + id + " no longer exists.", enfe);
            }
            Ofertas idOferta = seminarios.getIdOferta();
            if (idOferta != null) {
                idOferta.getSeminariosCollection().remove(seminarios);
                idOferta = em.merge(idOferta);
            }
            em.remove(seminarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Seminarios> findSeminariosEntities() {
        return findSeminariosEntities(true, -1, -1);
    }

    public List<Seminarios> findSeminariosEntities(int maxResults, int firstResult) {
        return findSeminariosEntities(false, maxResults, firstResult);
    }

    private List<Seminarios> findSeminariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Seminarios.class));
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

    public Seminarios findSeminarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Seminarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeminariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Seminarios> rt = cq.from(Seminarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
