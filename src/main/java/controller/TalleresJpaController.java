/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.Ofertas;
import java.util.ArrayList;
import java.util.List;
import model.Talleres;

/**
 *
 * @author Fahrid
 */
public class TalleresJpaController implements Serializable {

    public TalleresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Talleres talleres) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Ofertas ofertasOrphanCheck = talleres.getOfertas();
        if (ofertasOrphanCheck != null) {
            Talleres oldTalleresOfOfertas = ofertasOrphanCheck.getTalleres();
            if (oldTalleresOfOfertas != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Ofertas " + ofertasOrphanCheck + " already has an item of type Talleres whose ofertas column cannot be null. Please make another selection for the ofertas field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ofertas ofertas = talleres.getOfertas();
            if (ofertas != null) {
                ofertas = em.getReference(ofertas.getClass(), ofertas.getIdOferta());
                talleres.setOfertas(ofertas);
            }
            em.persist(talleres);
            if (ofertas != null) {
                ofertas.setTalleres(talleres);
                ofertas = em.merge(ofertas);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTalleres(talleres.getIdOferta()) != null) {
                throw new PreexistingEntityException("Talleres " + talleres + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Talleres talleres) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Talleres persistentTalleres = em.find(Talleres.class, talleres.getIdOferta());
            Ofertas ofertasOld = persistentTalleres.getOfertas();
            Ofertas ofertasNew = talleres.getOfertas();
            List<String> illegalOrphanMessages = null;
            if (ofertasNew != null && !ofertasNew.equals(ofertasOld)) {
                Talleres oldTalleresOfOfertas = ofertasNew.getTalleres();
                if (oldTalleresOfOfertas != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Ofertas " + ofertasNew + " already has an item of type Talleres whose ofertas column cannot be null. Please make another selection for the ofertas field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (ofertasNew != null) {
                ofertasNew = em.getReference(ofertasNew.getClass(), ofertasNew.getIdOferta());
                talleres.setOfertas(ofertasNew);
            }
            talleres = em.merge(talleres);
            if (ofertasOld != null && !ofertasOld.equals(ofertasNew)) {
                ofertasOld.setTalleres(null);
                ofertasOld = em.merge(ofertasOld);
            }
            if (ofertasNew != null && !ofertasNew.equals(ofertasOld)) {
                ofertasNew.setTalleres(talleres);
                ofertasNew = em.merge(ofertasNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = talleres.getIdOferta();
                if (findTalleres(id) == null) {
                    throw new NonexistentEntityException("The talleres with id " + id + " no longer exists.");
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
            Talleres talleres;
            try {
                talleres = em.getReference(Talleres.class, id);
                talleres.getIdOferta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The talleres with id " + id + " no longer exists.", enfe);
            }
            Ofertas ofertas = talleres.getOfertas();
            if (ofertas != null) {
                ofertas.setTalleres(null);
                ofertas = em.merge(ofertas);
            }
            em.remove(talleres);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Talleres> findTalleresEntities() {
        return findTalleresEntities(true, -1, -1);
    }

    public List<Talleres> findTalleresEntities(int maxResults, int firstResult) {
        return findTalleresEntities(false, maxResults, firstResult);
    }

    private List<Talleres> findTalleresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Talleres.class));
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

    public Talleres findTalleres(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Talleres.class, id);
        } finally {
            em.close();
        }
    }

    public int getTalleresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Talleres> rt = cq.from(Talleres.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
