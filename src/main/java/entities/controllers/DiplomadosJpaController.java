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
import model.Diplomados;
import model.Ofertas;

/**
 *
 * @author Fahrid
 */
public class DiplomadosJpaController implements Serializable {

    public DiplomadosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Diplomados diplomados) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ofertas idOferta = diplomados.getIdOferta();
            if (idOferta != null) {
                idOferta = em.getReference(idOferta.getClass(), idOferta.getIdOferta());
                diplomados.setIdOferta(idOferta);
            }
            em.persist(diplomados);
            if (idOferta != null) {
                idOferta.getDiplomadosCollection().add(diplomados);
                idOferta = em.merge(idOferta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDiplomados(diplomados.getIdDiplomado()) != null) {
                throw new PreexistingEntityException("Diplomados " + diplomados + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Diplomados diplomados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Diplomados persistentDiplomados = em.find(Diplomados.class, diplomados.getIdDiplomado());
            Ofertas idOfertaOld = persistentDiplomados.getIdOferta();
            Ofertas idOfertaNew = diplomados.getIdOferta();
            if (idOfertaNew != null) {
                idOfertaNew = em.getReference(idOfertaNew.getClass(), idOfertaNew.getIdOferta());
                diplomados.setIdOferta(idOfertaNew);
            }
            diplomados = em.merge(diplomados);
            if (idOfertaOld != null && !idOfertaOld.equals(idOfertaNew)) {
                idOfertaOld.getDiplomadosCollection().remove(diplomados);
                idOfertaOld = em.merge(idOfertaOld);
            }
            if (idOfertaNew != null && !idOfertaNew.equals(idOfertaOld)) {
                idOfertaNew.getDiplomadosCollection().add(diplomados);
                idOfertaNew = em.merge(idOfertaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = diplomados.getIdDiplomado();
                if (findDiplomados(id) == null) {
                    throw new NonexistentEntityException("The diplomados with id " + id + " no longer exists.");
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
            Diplomados diplomados;
            try {
                diplomados = em.getReference(Diplomados.class, id);
                diplomados.getIdDiplomado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The diplomados with id " + id + " no longer exists.", enfe);
            }
            Ofertas idOferta = diplomados.getIdOferta();
            if (idOferta != null) {
                idOferta.getDiplomadosCollection().remove(diplomados);
                idOferta = em.merge(idOferta);
            }
            em.remove(diplomados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Diplomados> findDiplomadosEntities() {
        return findDiplomadosEntities(true, -1, -1);
    }

    public List<Diplomados> findDiplomadosEntities(int maxResults, int firstResult) {
        return findDiplomadosEntities(false, maxResults, firstResult);
    }

    private List<Diplomados> findDiplomadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Diplomados.class));
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

    public Diplomados findDiplomados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Diplomados.class, id);
        } finally {
            em.close();
        }
    }

    public int getDiplomadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Diplomados> rt = cq.from(Diplomados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
