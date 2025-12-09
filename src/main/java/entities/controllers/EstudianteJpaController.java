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
import model.Inscripcion;
import model.Certificaciones;
import model.Estudiante;

/**
 *
 * @author Fahrid
 */
public class EstudianteJpaController implements Serializable {

    public EstudianteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estudiante estudiante) throws IllegalOrphanException {
        if (estudiante.getParticipacionesCollection() == null) {
            estudiante.setParticipacionesCollection(new ArrayList<Participaciones>());
        }
        if (estudiante.getInscripcionCollection() == null) {
            estudiante.setInscripcionCollection(new ArrayList<Inscripcion>());
        }
        if (estudiante.getCertificacionesCollection() == null) {
            estudiante.setCertificacionesCollection(new ArrayList<Certificaciones>());
        }
        List<String> illegalOrphanMessages = null;
        Usuario idUsuarioOrphanCheck = estudiante.getIdUsuario();
        if (idUsuarioOrphanCheck != null) {
            Estudiante oldEstudianteOfIdUsuario = idUsuarioOrphanCheck.getEstudiante();
            if (oldEstudianteOfIdUsuario != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Usuario " + idUsuarioOrphanCheck + " already has an item of type Estudiante whose idUsuario column cannot be null. Please make another selection for the idUsuario field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idUsuario = estudiante.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                estudiante.setIdUsuario(idUsuario);
            }
            Collection<Participaciones> attachedParticipacionesCollection = new ArrayList<Participaciones>();
            for (Participaciones participacionesCollectionParticipacionesToAttach : estudiante.getParticipacionesCollection()) {
                participacionesCollectionParticipacionesToAttach = em.getReference(participacionesCollectionParticipacionesToAttach.getClass(), participacionesCollectionParticipacionesToAttach.getIdParticipacion());
                attachedParticipacionesCollection.add(participacionesCollectionParticipacionesToAttach);
            }
            estudiante.setParticipacionesCollection(attachedParticipacionesCollection);
            Collection<Inscripcion> attachedInscripcionCollection = new ArrayList<Inscripcion>();
            for (Inscripcion inscripcionCollectionInscripcionToAttach : estudiante.getInscripcionCollection()) {
                inscripcionCollectionInscripcionToAttach = em.getReference(inscripcionCollectionInscripcionToAttach.getClass(), inscripcionCollectionInscripcionToAttach.getInscripcionPK());
                attachedInscripcionCollection.add(inscripcionCollectionInscripcionToAttach);
            }
            estudiante.setInscripcionCollection(attachedInscripcionCollection);
            Collection<Certificaciones> attachedCertificacionesCollection = new ArrayList<Certificaciones>();
            for (Certificaciones certificacionesCollectionCertificacionesToAttach : estudiante.getCertificacionesCollection()) {
                certificacionesCollectionCertificacionesToAttach = em.getReference(certificacionesCollectionCertificacionesToAttach.getClass(), certificacionesCollectionCertificacionesToAttach.getIdCertificado());
                attachedCertificacionesCollection.add(certificacionesCollectionCertificacionesToAttach);
            }
            estudiante.setCertificacionesCollection(attachedCertificacionesCollection);
            em.persist(estudiante);
            if (idUsuario != null) {
                idUsuario.setEstudiante(estudiante);
                idUsuario = em.merge(idUsuario);
            }
            for (Participaciones participacionesCollectionParticipaciones : estudiante.getParticipacionesCollection()) {
                Estudiante oldIdEstudianteOfParticipacionesCollectionParticipaciones = participacionesCollectionParticipaciones.getIdEstudiante();
                participacionesCollectionParticipaciones.setIdEstudiante(estudiante);
                participacionesCollectionParticipaciones = em.merge(participacionesCollectionParticipaciones);
                if (oldIdEstudianteOfParticipacionesCollectionParticipaciones != null) {
                    oldIdEstudianteOfParticipacionesCollectionParticipaciones.getParticipacionesCollection().remove(participacionesCollectionParticipaciones);
                    oldIdEstudianteOfParticipacionesCollectionParticipaciones = em.merge(oldIdEstudianteOfParticipacionesCollectionParticipaciones);
                }
            }
            for (Inscripcion inscripcionCollectionInscripcion : estudiante.getInscripcionCollection()) {
                Estudiante oldEstudianteOfInscripcionCollectionInscripcion = inscripcionCollectionInscripcion.getEstudiante();
                inscripcionCollectionInscripcion.setEstudiante(estudiante);
                inscripcionCollectionInscripcion = em.merge(inscripcionCollectionInscripcion);
                if (oldEstudianteOfInscripcionCollectionInscripcion != null) {
                    oldEstudianteOfInscripcionCollectionInscripcion.getInscripcionCollection().remove(inscripcionCollectionInscripcion);
                    oldEstudianteOfInscripcionCollectionInscripcion = em.merge(oldEstudianteOfInscripcionCollectionInscripcion);
                }
            }
            for (Certificaciones certificacionesCollectionCertificaciones : estudiante.getCertificacionesCollection()) {
                Estudiante oldIdEstudianteOfCertificacionesCollectionCertificaciones = certificacionesCollectionCertificaciones.getIdEstudiante();
                certificacionesCollectionCertificaciones.setIdEstudiante(estudiante);
                certificacionesCollectionCertificaciones = em.merge(certificacionesCollectionCertificaciones);
                if (oldIdEstudianteOfCertificacionesCollectionCertificaciones != null) {
                    oldIdEstudianteOfCertificacionesCollectionCertificaciones.getCertificacionesCollection().remove(certificacionesCollectionCertificaciones);
                    oldIdEstudianteOfCertificacionesCollectionCertificaciones = em.merge(oldIdEstudianteOfCertificacionesCollectionCertificaciones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estudiante estudiante) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiante persistentEstudiante = em.find(Estudiante.class, estudiante.getIdEstudiante());
            Usuario idUsuarioOld = persistentEstudiante.getIdUsuario();
            Usuario idUsuarioNew = estudiante.getIdUsuario();
            Collection<Participaciones> participacionesCollectionOld = persistentEstudiante.getParticipacionesCollection();
            Collection<Participaciones> participacionesCollectionNew = estudiante.getParticipacionesCollection();
            Collection<Inscripcion> inscripcionCollectionOld = persistentEstudiante.getInscripcionCollection();
            Collection<Inscripcion> inscripcionCollectionNew = estudiante.getInscripcionCollection();
            Collection<Certificaciones> certificacionesCollectionOld = persistentEstudiante.getCertificacionesCollection();
            Collection<Certificaciones> certificacionesCollectionNew = estudiante.getCertificacionesCollection();
            List<String> illegalOrphanMessages = null;
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                Estudiante oldEstudianteOfIdUsuario = idUsuarioNew.getEstudiante();
                if (oldEstudianteOfIdUsuario != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Usuario " + idUsuarioNew + " already has an item of type Estudiante whose idUsuario column cannot be null. Please make another selection for the idUsuario field.");
                }
            }
            for (Participaciones participacionesCollectionOldParticipaciones : participacionesCollectionOld) {
                if (!participacionesCollectionNew.contains(participacionesCollectionOldParticipaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Participaciones " + participacionesCollectionOldParticipaciones + " since its idEstudiante field is not nullable.");
                }
            }
            for (Inscripcion inscripcionCollectionOldInscripcion : inscripcionCollectionOld) {
                if (!inscripcionCollectionNew.contains(inscripcionCollectionOldInscripcion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Inscripcion " + inscripcionCollectionOldInscripcion + " since its estudiante field is not nullable.");
                }
            }
            for (Certificaciones certificacionesCollectionOldCertificaciones : certificacionesCollectionOld) {
                if (!certificacionesCollectionNew.contains(certificacionesCollectionOldCertificaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Certificaciones " + certificacionesCollectionOldCertificaciones + " since its idEstudiante field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                estudiante.setIdUsuario(idUsuarioNew);
            }
            Collection<Participaciones> attachedParticipacionesCollectionNew = new ArrayList<Participaciones>();
            for (Participaciones participacionesCollectionNewParticipacionesToAttach : participacionesCollectionNew) {
                participacionesCollectionNewParticipacionesToAttach = em.getReference(participacionesCollectionNewParticipacionesToAttach.getClass(), participacionesCollectionNewParticipacionesToAttach.getIdParticipacion());
                attachedParticipacionesCollectionNew.add(participacionesCollectionNewParticipacionesToAttach);
            }
            participacionesCollectionNew = attachedParticipacionesCollectionNew;
            estudiante.setParticipacionesCollection(participacionesCollectionNew);
            Collection<Inscripcion> attachedInscripcionCollectionNew = new ArrayList<Inscripcion>();
            for (Inscripcion inscripcionCollectionNewInscripcionToAttach : inscripcionCollectionNew) {
                inscripcionCollectionNewInscripcionToAttach = em.getReference(inscripcionCollectionNewInscripcionToAttach.getClass(), inscripcionCollectionNewInscripcionToAttach.getInscripcionPK());
                attachedInscripcionCollectionNew.add(inscripcionCollectionNewInscripcionToAttach);
            }
            inscripcionCollectionNew = attachedInscripcionCollectionNew;
            estudiante.setInscripcionCollection(inscripcionCollectionNew);
            Collection<Certificaciones> attachedCertificacionesCollectionNew = new ArrayList<Certificaciones>();
            for (Certificaciones certificacionesCollectionNewCertificacionesToAttach : certificacionesCollectionNew) {
                certificacionesCollectionNewCertificacionesToAttach = em.getReference(certificacionesCollectionNewCertificacionesToAttach.getClass(), certificacionesCollectionNewCertificacionesToAttach.getIdCertificado());
                attachedCertificacionesCollectionNew.add(certificacionesCollectionNewCertificacionesToAttach);
            }
            certificacionesCollectionNew = attachedCertificacionesCollectionNew;
            estudiante.setCertificacionesCollection(certificacionesCollectionNew);
            estudiante = em.merge(estudiante);
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.setEstudiante(null);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.setEstudiante(estudiante);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            for (Participaciones participacionesCollectionNewParticipaciones : participacionesCollectionNew) {
                if (!participacionesCollectionOld.contains(participacionesCollectionNewParticipaciones)) {
                    Estudiante oldIdEstudianteOfParticipacionesCollectionNewParticipaciones = participacionesCollectionNewParticipaciones.getIdEstudiante();
                    participacionesCollectionNewParticipaciones.setIdEstudiante(estudiante);
                    participacionesCollectionNewParticipaciones = em.merge(participacionesCollectionNewParticipaciones);
                    if (oldIdEstudianteOfParticipacionesCollectionNewParticipaciones != null && !oldIdEstudianteOfParticipacionesCollectionNewParticipaciones.equals(estudiante)) {
                        oldIdEstudianteOfParticipacionesCollectionNewParticipaciones.getParticipacionesCollection().remove(participacionesCollectionNewParticipaciones);
                        oldIdEstudianteOfParticipacionesCollectionNewParticipaciones = em.merge(oldIdEstudianteOfParticipacionesCollectionNewParticipaciones);
                    }
                }
            }
            for (Inscripcion inscripcionCollectionNewInscripcion : inscripcionCollectionNew) {
                if (!inscripcionCollectionOld.contains(inscripcionCollectionNewInscripcion)) {
                    Estudiante oldEstudianteOfInscripcionCollectionNewInscripcion = inscripcionCollectionNewInscripcion.getEstudiante();
                    inscripcionCollectionNewInscripcion.setEstudiante(estudiante);
                    inscripcionCollectionNewInscripcion = em.merge(inscripcionCollectionNewInscripcion);
                    if (oldEstudianteOfInscripcionCollectionNewInscripcion != null && !oldEstudianteOfInscripcionCollectionNewInscripcion.equals(estudiante)) {
                        oldEstudianteOfInscripcionCollectionNewInscripcion.getInscripcionCollection().remove(inscripcionCollectionNewInscripcion);
                        oldEstudianteOfInscripcionCollectionNewInscripcion = em.merge(oldEstudianteOfInscripcionCollectionNewInscripcion);
                    }
                }
            }
            for (Certificaciones certificacionesCollectionNewCertificaciones : certificacionesCollectionNew) {
                if (!certificacionesCollectionOld.contains(certificacionesCollectionNewCertificaciones)) {
                    Estudiante oldIdEstudianteOfCertificacionesCollectionNewCertificaciones = certificacionesCollectionNewCertificaciones.getIdEstudiante();
                    certificacionesCollectionNewCertificaciones.setIdEstudiante(estudiante);
                    certificacionesCollectionNewCertificaciones = em.merge(certificacionesCollectionNewCertificaciones);
                    if (oldIdEstudianteOfCertificacionesCollectionNewCertificaciones != null && !oldIdEstudianteOfCertificacionesCollectionNewCertificaciones.equals(estudiante)) {
                        oldIdEstudianteOfCertificacionesCollectionNewCertificaciones.getCertificacionesCollection().remove(certificacionesCollectionNewCertificaciones);
                        oldIdEstudianteOfCertificacionesCollectionNewCertificaciones = em.merge(oldIdEstudianteOfCertificacionesCollectionNewCertificaciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estudiante.getIdEstudiante();
                if (findEstudiante(id) == null) {
                    throw new NonexistentEntityException("The estudiante with id " + id + " no longer exists.");
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
            Estudiante estudiante;
            try {
                estudiante = em.getReference(Estudiante.class, id);
                estudiante.getIdEstudiante();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estudiante with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Participaciones> participacionesCollectionOrphanCheck = estudiante.getParticipacionesCollection();
            for (Participaciones participacionesCollectionOrphanCheckParticipaciones : participacionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estudiante (" + estudiante + ") cannot be destroyed since the Participaciones " + participacionesCollectionOrphanCheckParticipaciones + " in its participacionesCollection field has a non-nullable idEstudiante field.");
            }
            Collection<Inscripcion> inscripcionCollectionOrphanCheck = estudiante.getInscripcionCollection();
            for (Inscripcion inscripcionCollectionOrphanCheckInscripcion : inscripcionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estudiante (" + estudiante + ") cannot be destroyed since the Inscripcion " + inscripcionCollectionOrphanCheckInscripcion + " in its inscripcionCollection field has a non-nullable estudiante field.");
            }
            Collection<Certificaciones> certificacionesCollectionOrphanCheck = estudiante.getCertificacionesCollection();
            for (Certificaciones certificacionesCollectionOrphanCheckCertificaciones : certificacionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estudiante (" + estudiante + ") cannot be destroyed since the Certificaciones " + certificacionesCollectionOrphanCheckCertificaciones + " in its certificacionesCollection field has a non-nullable idEstudiante field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario idUsuario = estudiante.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.setEstudiante(null);
                idUsuario = em.merge(idUsuario);
            }
            em.remove(estudiante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estudiante> findEstudianteEntities() {
        return findEstudianteEntities(true, -1, -1);
    }

    public List<Estudiante> findEstudianteEntities(int maxResults, int firstResult) {
        return findEstudianteEntities(false, maxResults, firstResult);
    }

    private List<Estudiante> findEstudianteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estudiante.class));
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

    public Estudiante findEstudiante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estudiante.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstudianteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estudiante> rt = cq.from(Estudiante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
