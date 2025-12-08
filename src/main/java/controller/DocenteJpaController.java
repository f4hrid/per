/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.Usuario;
import model.Participaciones;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import model.Ofertas;
import model.Certificaciones;
import model.Docente;

/**
 *
 * @author Fahrid
 */
public class DocenteJpaController implements Serializable {

    public DocenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Docente docente) throws IllegalOrphanException {
        if (docente.getParticipacionesCollection() == null) {
            docente.setParticipacionesCollection(new ArrayList<Participaciones>());
        }
        if (docente.getOfertasCollection() == null) {
            docente.setOfertasCollection(new ArrayList<Ofertas>());
        }
        if (docente.getCertificacionesCollection() == null) {
            docente.setCertificacionesCollection(new ArrayList<Certificaciones>());
        }
        List<String> illegalOrphanMessages = null;
        Usuario idUsuarioOrphanCheck = docente.getIdUsuario();
        if (idUsuarioOrphanCheck != null) {
            Docente oldDocenteOfIdUsuario = idUsuarioOrphanCheck.getDocente();
            if (oldDocenteOfIdUsuario != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Usuario " + idUsuarioOrphanCheck + " already has an item of type Docente whose idUsuario column cannot be null. Please make another selection for the idUsuario field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idUsuario = docente.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                docente.setIdUsuario(idUsuario);
            }
            Collection<Participaciones> attachedParticipacionesCollection = new ArrayList<Participaciones>();
            for (Participaciones participacionesCollectionParticipacionesToAttach : docente.getParticipacionesCollection()) {
                participacionesCollectionParticipacionesToAttach = em.getReference(participacionesCollectionParticipacionesToAttach.getClass(), participacionesCollectionParticipacionesToAttach.getIdParticipacion());
                attachedParticipacionesCollection.add(participacionesCollectionParticipacionesToAttach);
            }
            docente.setParticipacionesCollection(attachedParticipacionesCollection);
            Collection<Ofertas> attachedOfertasCollection = new ArrayList<Ofertas>();
            for (Ofertas ofertasCollectionOfertasToAttach : docente.getOfertasCollection()) {
                ofertasCollectionOfertasToAttach = em.getReference(ofertasCollectionOfertasToAttach.getClass(), ofertasCollectionOfertasToAttach.getIdOferta());
                attachedOfertasCollection.add(ofertasCollectionOfertasToAttach);
            }
            docente.setOfertasCollection(attachedOfertasCollection);
            Collection<Certificaciones> attachedCertificacionesCollection = new ArrayList<Certificaciones>();
            for (Certificaciones certificacionesCollectionCertificacionesToAttach : docente.getCertificacionesCollection()) {
                certificacionesCollectionCertificacionesToAttach = em.getReference(certificacionesCollectionCertificacionesToAttach.getClass(), certificacionesCollectionCertificacionesToAttach.getIdCertificado());
                attachedCertificacionesCollection.add(certificacionesCollectionCertificacionesToAttach);
            }
            docente.setCertificacionesCollection(attachedCertificacionesCollection);
            em.persist(docente);
            if (idUsuario != null) {
                idUsuario.setDocente(docente);
                idUsuario = em.merge(idUsuario);
            }
            for (Participaciones participacionesCollectionParticipaciones : docente.getParticipacionesCollection()) {
                Docente oldIdDocenteOfParticipacionesCollectionParticipaciones = participacionesCollectionParticipaciones.getIdDocente();
                participacionesCollectionParticipaciones.setIdDocente(docente);
                participacionesCollectionParticipaciones = em.merge(participacionesCollectionParticipaciones);
                if (oldIdDocenteOfParticipacionesCollectionParticipaciones != null) {
                    oldIdDocenteOfParticipacionesCollectionParticipaciones.getParticipacionesCollection().remove(participacionesCollectionParticipaciones);
                    oldIdDocenteOfParticipacionesCollectionParticipaciones = em.merge(oldIdDocenteOfParticipacionesCollectionParticipaciones);
                }
            }
            for (Ofertas ofertasCollectionOfertas : docente.getOfertasCollection()) {
                Docente oldIdDocenteOfOfertasCollectionOfertas = ofertasCollectionOfertas.getIdDocente();
                ofertasCollectionOfertas.setIdDocente(docente);
                ofertasCollectionOfertas = em.merge(ofertasCollectionOfertas);
                if (oldIdDocenteOfOfertasCollectionOfertas != null) {
                    oldIdDocenteOfOfertasCollectionOfertas.getOfertasCollection().remove(ofertasCollectionOfertas);
                    oldIdDocenteOfOfertasCollectionOfertas = em.merge(oldIdDocenteOfOfertasCollectionOfertas);
                }
            }
            for (Certificaciones certificacionesCollectionCertificaciones : docente.getCertificacionesCollection()) {
                Docente oldIdDocenteOfCertificacionesCollectionCertificaciones = certificacionesCollectionCertificaciones.getIdDocente();
                certificacionesCollectionCertificaciones.setIdDocente(docente);
                certificacionesCollectionCertificaciones = em.merge(certificacionesCollectionCertificaciones);
                if (oldIdDocenteOfCertificacionesCollectionCertificaciones != null) {
                    oldIdDocenteOfCertificacionesCollectionCertificaciones.getCertificacionesCollection().remove(certificacionesCollectionCertificaciones);
                    oldIdDocenteOfCertificacionesCollectionCertificaciones = em.merge(oldIdDocenteOfCertificacionesCollectionCertificaciones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Docente docente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docente persistentDocente = em.find(Docente.class, docente.getIdDocente());
            Usuario idUsuarioOld = persistentDocente.getIdUsuario();
            Usuario idUsuarioNew = docente.getIdUsuario();
            Collection<Participaciones> participacionesCollectionOld = persistentDocente.getParticipacionesCollection();
            Collection<Participaciones> participacionesCollectionNew = docente.getParticipacionesCollection();
            Collection<Ofertas> ofertasCollectionOld = persistentDocente.getOfertasCollection();
            Collection<Ofertas> ofertasCollectionNew = docente.getOfertasCollection();
            Collection<Certificaciones> certificacionesCollectionOld = persistentDocente.getCertificacionesCollection();
            Collection<Certificaciones> certificacionesCollectionNew = docente.getCertificacionesCollection();
            List<String> illegalOrphanMessages = null;
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                Docente oldDocenteOfIdUsuario = idUsuarioNew.getDocente();
                if (oldDocenteOfIdUsuario != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Usuario " + idUsuarioNew + " already has an item of type Docente whose idUsuario column cannot be null. Please make another selection for the idUsuario field.");
                }
            }
            for (Participaciones participacionesCollectionOldParticipaciones : participacionesCollectionOld) {
                if (!participacionesCollectionNew.contains(participacionesCollectionOldParticipaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Participaciones " + participacionesCollectionOldParticipaciones + " since its idDocente field is not nullable.");
                }
            }
            for (Certificaciones certificacionesCollectionOldCertificaciones : certificacionesCollectionOld) {
                if (!certificacionesCollectionNew.contains(certificacionesCollectionOldCertificaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Certificaciones " + certificacionesCollectionOldCertificaciones + " since its idDocente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                docente.setIdUsuario(idUsuarioNew);
            }
            Collection<Participaciones> attachedParticipacionesCollectionNew = new ArrayList<Participaciones>();
            for (Participaciones participacionesCollectionNewParticipacionesToAttach : participacionesCollectionNew) {
                participacionesCollectionNewParticipacionesToAttach = em.getReference(participacionesCollectionNewParticipacionesToAttach.getClass(), participacionesCollectionNewParticipacionesToAttach.getIdParticipacion());
                attachedParticipacionesCollectionNew.add(participacionesCollectionNewParticipacionesToAttach);
            }
            participacionesCollectionNew = attachedParticipacionesCollectionNew;
            docente.setParticipacionesCollection(participacionesCollectionNew);
            Collection<Ofertas> attachedOfertasCollectionNew = new ArrayList<Ofertas>();
            for (Ofertas ofertasCollectionNewOfertasToAttach : ofertasCollectionNew) {
                ofertasCollectionNewOfertasToAttach = em.getReference(ofertasCollectionNewOfertasToAttach.getClass(), ofertasCollectionNewOfertasToAttach.getIdOferta());
                attachedOfertasCollectionNew.add(ofertasCollectionNewOfertasToAttach);
            }
            ofertasCollectionNew = attachedOfertasCollectionNew;
            docente.setOfertasCollection(ofertasCollectionNew);
            Collection<Certificaciones> attachedCertificacionesCollectionNew = new ArrayList<Certificaciones>();
            for (Certificaciones certificacionesCollectionNewCertificacionesToAttach : certificacionesCollectionNew) {
                certificacionesCollectionNewCertificacionesToAttach = em.getReference(certificacionesCollectionNewCertificacionesToAttach.getClass(), certificacionesCollectionNewCertificacionesToAttach.getIdCertificado());
                attachedCertificacionesCollectionNew.add(certificacionesCollectionNewCertificacionesToAttach);
            }
            certificacionesCollectionNew = attachedCertificacionesCollectionNew;
            docente.setCertificacionesCollection(certificacionesCollectionNew);
            docente = em.merge(docente);
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.setDocente(null);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.setDocente(docente);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            for (Participaciones participacionesCollectionNewParticipaciones : participacionesCollectionNew) {
                if (!participacionesCollectionOld.contains(participacionesCollectionNewParticipaciones)) {
                    Docente oldIdDocenteOfParticipacionesCollectionNewParticipaciones = participacionesCollectionNewParticipaciones.getIdDocente();
                    participacionesCollectionNewParticipaciones.setIdDocente(docente);
                    participacionesCollectionNewParticipaciones = em.merge(participacionesCollectionNewParticipaciones);
                    if (oldIdDocenteOfParticipacionesCollectionNewParticipaciones != null && !oldIdDocenteOfParticipacionesCollectionNewParticipaciones.equals(docente)) {
                        oldIdDocenteOfParticipacionesCollectionNewParticipaciones.getParticipacionesCollection().remove(participacionesCollectionNewParticipaciones);
                        oldIdDocenteOfParticipacionesCollectionNewParticipaciones = em.merge(oldIdDocenteOfParticipacionesCollectionNewParticipaciones);
                    }
                }
            }
            for (Ofertas ofertasCollectionOldOfertas : ofertasCollectionOld) {
                if (!ofertasCollectionNew.contains(ofertasCollectionOldOfertas)) {
                    ofertasCollectionOldOfertas.setIdDocente(null);
                    ofertasCollectionOldOfertas = em.merge(ofertasCollectionOldOfertas);
                }
            }
            for (Ofertas ofertasCollectionNewOfertas : ofertasCollectionNew) {
                if (!ofertasCollectionOld.contains(ofertasCollectionNewOfertas)) {
                    Docente oldIdDocenteOfOfertasCollectionNewOfertas = ofertasCollectionNewOfertas.getIdDocente();
                    ofertasCollectionNewOfertas.setIdDocente(docente);
                    ofertasCollectionNewOfertas = em.merge(ofertasCollectionNewOfertas);
                    if (oldIdDocenteOfOfertasCollectionNewOfertas != null && !oldIdDocenteOfOfertasCollectionNewOfertas.equals(docente)) {
                        oldIdDocenteOfOfertasCollectionNewOfertas.getOfertasCollection().remove(ofertasCollectionNewOfertas);
                        oldIdDocenteOfOfertasCollectionNewOfertas = em.merge(oldIdDocenteOfOfertasCollectionNewOfertas);
                    }
                }
            }
            for (Certificaciones certificacionesCollectionNewCertificaciones : certificacionesCollectionNew) {
                if (!certificacionesCollectionOld.contains(certificacionesCollectionNewCertificaciones)) {
                    Docente oldIdDocenteOfCertificacionesCollectionNewCertificaciones = certificacionesCollectionNewCertificaciones.getIdDocente();
                    certificacionesCollectionNewCertificaciones.setIdDocente(docente);
                    certificacionesCollectionNewCertificaciones = em.merge(certificacionesCollectionNewCertificaciones);
                    if (oldIdDocenteOfCertificacionesCollectionNewCertificaciones != null && !oldIdDocenteOfCertificacionesCollectionNewCertificaciones.equals(docente)) {
                        oldIdDocenteOfCertificacionesCollectionNewCertificaciones.getCertificacionesCollection().remove(certificacionesCollectionNewCertificaciones);
                        oldIdDocenteOfCertificacionesCollectionNewCertificaciones = em.merge(oldIdDocenteOfCertificacionesCollectionNewCertificaciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = docente.getIdDocente();
                if (findDocente(id) == null) {
                    throw new NonexistentEntityException("The docente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docente docente;
            try {
                docente = em.getReference(Docente.class, id);
                docente.getIdDocente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The docente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Participaciones> participacionesCollectionOrphanCheck = docente.getParticipacionesCollection();
            for (Participaciones participacionesCollectionOrphanCheckParticipaciones : participacionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Docente (" + docente + ") cannot be destroyed since the Participaciones " + participacionesCollectionOrphanCheckParticipaciones + " in its participacionesCollection field has a non-nullable idDocente field.");
            }
            Collection<Certificaciones> certificacionesCollectionOrphanCheck = docente.getCertificacionesCollection();
            for (Certificaciones certificacionesCollectionOrphanCheckCertificaciones : certificacionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Docente (" + docente + ") cannot be destroyed since the Certificaciones " + certificacionesCollectionOrphanCheckCertificaciones + " in its certificacionesCollection field has a non-nullable idDocente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario idUsuario = docente.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.setDocente(null);
                idUsuario = em.merge(idUsuario);
            }
            Collection<Ofertas> ofertasCollection = docente.getOfertasCollection();
            for (Ofertas ofertasCollectionOfertas : ofertasCollection) {
                ofertasCollectionOfertas.setIdDocente(null);
                ofertasCollectionOfertas = em.merge(ofertasCollectionOfertas);
            }
            em.remove(docente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Docente> findDocenteEntities() {
        return findDocenteEntities(true, -1, -1);
    }

    public List<Docente> findDocenteEntities(int maxResults, int firstResult) {
        return findDocenteEntities(false, maxResults, firstResult);
    }

    private List<Docente> findDocenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Docente.class));
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

    public Docente findDocente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Docente.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Docente> rt = cq.from(Docente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
