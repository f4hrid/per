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
import model.Docente;
import model.Talleres;
import model.Participaciones;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import model.Cursos;
import model.Seminarios;
import model.Inscripcion;
import model.Certificaciones;
import model.Diplomados;
import model.Ofertas;

/**
 *
 * @author Fahrid
 */
public class OfertasJpaController implements Serializable {

    public OfertasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ofertas ofertas) throws PreexistingEntityException, Exception {
        if (ofertas.getParticipacionesCollection() == null) {
            ofertas.setParticipacionesCollection(new ArrayList<Participaciones>());
        }
        if (ofertas.getCursosCollection() == null) {
            ofertas.setCursosCollection(new ArrayList<Cursos>());
        }
        if (ofertas.getSeminariosCollection() == null) {
            ofertas.setSeminariosCollection(new ArrayList<Seminarios>());
        }
        if (ofertas.getInscripcionCollection() == null) {
            ofertas.setInscripcionCollection(new ArrayList<Inscripcion>());
        }
        if (ofertas.getCertificacionesCollection() == null) {
            ofertas.setCertificacionesCollection(new ArrayList<Certificaciones>());
        }
        if (ofertas.getDiplomadosCollection() == null) {
            ofertas.setDiplomadosCollection(new ArrayList<Diplomados>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docente idDocente = ofertas.getIdDocente();
            if (idDocente != null) {
                idDocente = em.getReference(idDocente.getClass(), idDocente.getIdDocente());
                ofertas.setIdDocente(idDocente);
            }
            Talleres talleres = ofertas.getTalleres();
            if (talleres != null) {
                talleres = em.getReference(talleres.getClass(), talleres.getIdOferta());
                ofertas.setTalleres(talleres);
            }
            Collection<Participaciones> attachedParticipacionesCollection = new ArrayList<Participaciones>();
            for (Participaciones participacionesCollectionParticipacionesToAttach : ofertas.getParticipacionesCollection()) {
                participacionesCollectionParticipacionesToAttach = em.getReference(participacionesCollectionParticipacionesToAttach.getClass(), participacionesCollectionParticipacionesToAttach.getIdParticipacion());
                attachedParticipacionesCollection.add(participacionesCollectionParticipacionesToAttach);
            }
            ofertas.setParticipacionesCollection(attachedParticipacionesCollection);
            Collection<Cursos> attachedCursosCollection = new ArrayList<Cursos>();
            for (Cursos cursosCollectionCursosToAttach : ofertas.getCursosCollection()) {
                cursosCollectionCursosToAttach = em.getReference(cursosCollectionCursosToAttach.getClass(), cursosCollectionCursosToAttach.getIdCurso());
                attachedCursosCollection.add(cursosCollectionCursosToAttach);
            }
            ofertas.setCursosCollection(attachedCursosCollection);
            Collection<Seminarios> attachedSeminariosCollection = new ArrayList<Seminarios>();
            for (Seminarios seminariosCollectionSeminariosToAttach : ofertas.getSeminariosCollection()) {
                seminariosCollectionSeminariosToAttach = em.getReference(seminariosCollectionSeminariosToAttach.getClass(), seminariosCollectionSeminariosToAttach.getIdSeminario());
                attachedSeminariosCollection.add(seminariosCollectionSeminariosToAttach);
            }
            ofertas.setSeminariosCollection(attachedSeminariosCollection);
            Collection<Inscripcion> attachedInscripcionCollection = new ArrayList<Inscripcion>();
            for (Inscripcion inscripcionCollectionInscripcionToAttach : ofertas.getInscripcionCollection()) {
                inscripcionCollectionInscripcionToAttach = em.getReference(inscripcionCollectionInscripcionToAttach.getClass(), inscripcionCollectionInscripcionToAttach.getInscripcionPK());
                attachedInscripcionCollection.add(inscripcionCollectionInscripcionToAttach);
            }
            ofertas.setInscripcionCollection(attachedInscripcionCollection);
            Collection<Certificaciones> attachedCertificacionesCollection = new ArrayList<Certificaciones>();
            for (Certificaciones certificacionesCollectionCertificacionesToAttach : ofertas.getCertificacionesCollection()) {
                certificacionesCollectionCertificacionesToAttach = em.getReference(certificacionesCollectionCertificacionesToAttach.getClass(), certificacionesCollectionCertificacionesToAttach.getIdCertificado());
                attachedCertificacionesCollection.add(certificacionesCollectionCertificacionesToAttach);
            }
            ofertas.setCertificacionesCollection(attachedCertificacionesCollection);
            Collection<Diplomados> attachedDiplomadosCollection = new ArrayList<Diplomados>();
            for (Diplomados diplomadosCollectionDiplomadosToAttach : ofertas.getDiplomadosCollection()) {
                diplomadosCollectionDiplomadosToAttach = em.getReference(diplomadosCollectionDiplomadosToAttach.getClass(), diplomadosCollectionDiplomadosToAttach.getIdDiplomado());
                attachedDiplomadosCollection.add(diplomadosCollectionDiplomadosToAttach);
            }
            ofertas.setDiplomadosCollection(attachedDiplomadosCollection);
            em.persist(ofertas);
            if (idDocente != null) {
                idDocente.getOfertasCollection().add(ofertas);
                idDocente = em.merge(idDocente);
            }
            if (talleres != null) {
                Ofertas oldOfertasOfTalleres = talleres.getOfertas();
                if (oldOfertasOfTalleres != null) {
                    oldOfertasOfTalleres.setTalleres(null);
                    oldOfertasOfTalleres = em.merge(oldOfertasOfTalleres);
                }
                talleres.setOfertas(ofertas);
                talleres = em.merge(talleres);
            }
            for (Participaciones participacionesCollectionParticipaciones : ofertas.getParticipacionesCollection()) {
                Ofertas oldIdOfertaOfParticipacionesCollectionParticipaciones = participacionesCollectionParticipaciones.getIdOferta();
                participacionesCollectionParticipaciones.setIdOferta(ofertas);
                participacionesCollectionParticipaciones = em.merge(participacionesCollectionParticipaciones);
                if (oldIdOfertaOfParticipacionesCollectionParticipaciones != null) {
                    oldIdOfertaOfParticipacionesCollectionParticipaciones.getParticipacionesCollection().remove(participacionesCollectionParticipaciones);
                    oldIdOfertaOfParticipacionesCollectionParticipaciones = em.merge(oldIdOfertaOfParticipacionesCollectionParticipaciones);
                }
            }
            for (Cursos cursosCollectionCursos : ofertas.getCursosCollection()) {
                Ofertas oldIdOfertaOfCursosCollectionCursos = cursosCollectionCursos.getIdOferta();
                cursosCollectionCursos.setIdOferta(ofertas);
                cursosCollectionCursos = em.merge(cursosCollectionCursos);
                if (oldIdOfertaOfCursosCollectionCursos != null) {
                    oldIdOfertaOfCursosCollectionCursos.getCursosCollection().remove(cursosCollectionCursos);
                    oldIdOfertaOfCursosCollectionCursos = em.merge(oldIdOfertaOfCursosCollectionCursos);
                }
            }
            for (Seminarios seminariosCollectionSeminarios : ofertas.getSeminariosCollection()) {
                Ofertas oldIdOfertaOfSeminariosCollectionSeminarios = seminariosCollectionSeminarios.getIdOferta();
                seminariosCollectionSeminarios.setIdOferta(ofertas);
                seminariosCollectionSeminarios = em.merge(seminariosCollectionSeminarios);
                if (oldIdOfertaOfSeminariosCollectionSeminarios != null) {
                    oldIdOfertaOfSeminariosCollectionSeminarios.getSeminariosCollection().remove(seminariosCollectionSeminarios);
                    oldIdOfertaOfSeminariosCollectionSeminarios = em.merge(oldIdOfertaOfSeminariosCollectionSeminarios);
                }
            }
            for (Inscripcion inscripcionCollectionInscripcion : ofertas.getInscripcionCollection()) {
                Ofertas oldOfertasOfInscripcionCollectionInscripcion = inscripcionCollectionInscripcion.getOfertas();
                inscripcionCollectionInscripcion.setOfertas(ofertas);
                inscripcionCollectionInscripcion = em.merge(inscripcionCollectionInscripcion);
                if (oldOfertasOfInscripcionCollectionInscripcion != null) {
                    oldOfertasOfInscripcionCollectionInscripcion.getInscripcionCollection().remove(inscripcionCollectionInscripcion);
                    oldOfertasOfInscripcionCollectionInscripcion = em.merge(oldOfertasOfInscripcionCollectionInscripcion);
                }
            }
            for (Certificaciones certificacionesCollectionCertificaciones : ofertas.getCertificacionesCollection()) {
                Ofertas oldIdOfertaOfCertificacionesCollectionCertificaciones = certificacionesCollectionCertificaciones.getIdOferta();
                certificacionesCollectionCertificaciones.setIdOferta(ofertas);
                certificacionesCollectionCertificaciones = em.merge(certificacionesCollectionCertificaciones);
                if (oldIdOfertaOfCertificacionesCollectionCertificaciones != null) {
                    oldIdOfertaOfCertificacionesCollectionCertificaciones.getCertificacionesCollection().remove(certificacionesCollectionCertificaciones);
                    oldIdOfertaOfCertificacionesCollectionCertificaciones = em.merge(oldIdOfertaOfCertificacionesCollectionCertificaciones);
                }
            }
            for (Diplomados diplomadosCollectionDiplomados : ofertas.getDiplomadosCollection()) {
                Ofertas oldIdOfertaOfDiplomadosCollectionDiplomados = diplomadosCollectionDiplomados.getIdOferta();
                diplomadosCollectionDiplomados.setIdOferta(ofertas);
                diplomadosCollectionDiplomados = em.merge(diplomadosCollectionDiplomados);
                if (oldIdOfertaOfDiplomadosCollectionDiplomados != null) {
                    oldIdOfertaOfDiplomadosCollectionDiplomados.getDiplomadosCollection().remove(diplomadosCollectionDiplomados);
                    oldIdOfertaOfDiplomadosCollectionDiplomados = em.merge(oldIdOfertaOfDiplomadosCollectionDiplomados);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOfertas(ofertas.getIdOferta()) != null) {
                throw new PreexistingEntityException("Ofertas " + ofertas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ofertas ofertas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ofertas persistentOfertas = em.find(Ofertas.class, ofertas.getIdOferta());
            Docente idDocenteOld = persistentOfertas.getIdDocente();
            Docente idDocenteNew = ofertas.getIdDocente();
            Talleres talleresOld = persistentOfertas.getTalleres();
            Talleres talleresNew = ofertas.getTalleres();
            Collection<Participaciones> participacionesCollectionOld = persistentOfertas.getParticipacionesCollection();
            Collection<Participaciones> participacionesCollectionNew = ofertas.getParticipacionesCollection();
            Collection<Cursos> cursosCollectionOld = persistentOfertas.getCursosCollection();
            Collection<Cursos> cursosCollectionNew = ofertas.getCursosCollection();
            Collection<Seminarios> seminariosCollectionOld = persistentOfertas.getSeminariosCollection();
            Collection<Seminarios> seminariosCollectionNew = ofertas.getSeminariosCollection();
            Collection<Inscripcion> inscripcionCollectionOld = persistentOfertas.getInscripcionCollection();
            Collection<Inscripcion> inscripcionCollectionNew = ofertas.getInscripcionCollection();
            Collection<Certificaciones> certificacionesCollectionOld = persistentOfertas.getCertificacionesCollection();
            Collection<Certificaciones> certificacionesCollectionNew = ofertas.getCertificacionesCollection();
            Collection<Diplomados> diplomadosCollectionOld = persistentOfertas.getDiplomadosCollection();
            Collection<Diplomados> diplomadosCollectionNew = ofertas.getDiplomadosCollection();
            List<String> illegalOrphanMessages = null;
            if (talleresOld != null && !talleresOld.equals(talleresNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Talleres " + talleresOld + " since its ofertas field is not nullable.");
            }
            for (Participaciones participacionesCollectionOldParticipaciones : participacionesCollectionOld) {
                if (!participacionesCollectionNew.contains(participacionesCollectionOldParticipaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Participaciones " + participacionesCollectionOldParticipaciones + " since its idOferta field is not nullable.");
                }
            }
            for (Cursos cursosCollectionOldCursos : cursosCollectionOld) {
                if (!cursosCollectionNew.contains(cursosCollectionOldCursos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cursos " + cursosCollectionOldCursos + " since its idOferta field is not nullable.");
                }
            }
            for (Seminarios seminariosCollectionOldSeminarios : seminariosCollectionOld) {
                if (!seminariosCollectionNew.contains(seminariosCollectionOldSeminarios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Seminarios " + seminariosCollectionOldSeminarios + " since its idOferta field is not nullable.");
                }
            }
            for (Inscripcion inscripcionCollectionOldInscripcion : inscripcionCollectionOld) {
                if (!inscripcionCollectionNew.contains(inscripcionCollectionOldInscripcion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Inscripcion " + inscripcionCollectionOldInscripcion + " since its ofertas field is not nullable.");
                }
            }
            for (Certificaciones certificacionesCollectionOldCertificaciones : certificacionesCollectionOld) {
                if (!certificacionesCollectionNew.contains(certificacionesCollectionOldCertificaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Certificaciones " + certificacionesCollectionOldCertificaciones + " since its idOferta field is not nullable.");
                }
            }
            for (Diplomados diplomadosCollectionOldDiplomados : diplomadosCollectionOld) {
                if (!diplomadosCollectionNew.contains(diplomadosCollectionOldDiplomados)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Diplomados " + diplomadosCollectionOldDiplomados + " since its idOferta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idDocenteNew != null) {
                idDocenteNew = em.getReference(idDocenteNew.getClass(), idDocenteNew.getIdDocente());
                ofertas.setIdDocente(idDocenteNew);
            }
            if (talleresNew != null) {
                talleresNew = em.getReference(talleresNew.getClass(), talleresNew.getIdOferta());
                ofertas.setTalleres(talleresNew);
            }
            Collection<Participaciones> attachedParticipacionesCollectionNew = new ArrayList<Participaciones>();
            for (Participaciones participacionesCollectionNewParticipacionesToAttach : participacionesCollectionNew) {
                participacionesCollectionNewParticipacionesToAttach = em.getReference(participacionesCollectionNewParticipacionesToAttach.getClass(), participacionesCollectionNewParticipacionesToAttach.getIdParticipacion());
                attachedParticipacionesCollectionNew.add(participacionesCollectionNewParticipacionesToAttach);
            }
            participacionesCollectionNew = attachedParticipacionesCollectionNew;
            ofertas.setParticipacionesCollection(participacionesCollectionNew);
            Collection<Cursos> attachedCursosCollectionNew = new ArrayList<Cursos>();
            for (Cursos cursosCollectionNewCursosToAttach : cursosCollectionNew) {
                cursosCollectionNewCursosToAttach = em.getReference(cursosCollectionNewCursosToAttach.getClass(), cursosCollectionNewCursosToAttach.getIdCurso());
                attachedCursosCollectionNew.add(cursosCollectionNewCursosToAttach);
            }
            cursosCollectionNew = attachedCursosCollectionNew;
            ofertas.setCursosCollection(cursosCollectionNew);
            Collection<Seminarios> attachedSeminariosCollectionNew = new ArrayList<Seminarios>();
            for (Seminarios seminariosCollectionNewSeminariosToAttach : seminariosCollectionNew) {
                seminariosCollectionNewSeminariosToAttach = em.getReference(seminariosCollectionNewSeminariosToAttach.getClass(), seminariosCollectionNewSeminariosToAttach.getIdSeminario());
                attachedSeminariosCollectionNew.add(seminariosCollectionNewSeminariosToAttach);
            }
            seminariosCollectionNew = attachedSeminariosCollectionNew;
            ofertas.setSeminariosCollection(seminariosCollectionNew);
            Collection<Inscripcion> attachedInscripcionCollectionNew = new ArrayList<Inscripcion>();
            for (Inscripcion inscripcionCollectionNewInscripcionToAttach : inscripcionCollectionNew) {
                inscripcionCollectionNewInscripcionToAttach = em.getReference(inscripcionCollectionNewInscripcionToAttach.getClass(), inscripcionCollectionNewInscripcionToAttach.getInscripcionPK());
                attachedInscripcionCollectionNew.add(inscripcionCollectionNewInscripcionToAttach);
            }
            inscripcionCollectionNew = attachedInscripcionCollectionNew;
            ofertas.setInscripcionCollection(inscripcionCollectionNew);
            Collection<Certificaciones> attachedCertificacionesCollectionNew = new ArrayList<Certificaciones>();
            for (Certificaciones certificacionesCollectionNewCertificacionesToAttach : certificacionesCollectionNew) {
                certificacionesCollectionNewCertificacionesToAttach = em.getReference(certificacionesCollectionNewCertificacionesToAttach.getClass(), certificacionesCollectionNewCertificacionesToAttach.getIdCertificado());
                attachedCertificacionesCollectionNew.add(certificacionesCollectionNewCertificacionesToAttach);
            }
            certificacionesCollectionNew = attachedCertificacionesCollectionNew;
            ofertas.setCertificacionesCollection(certificacionesCollectionNew);
            Collection<Diplomados> attachedDiplomadosCollectionNew = new ArrayList<Diplomados>();
            for (Diplomados diplomadosCollectionNewDiplomadosToAttach : diplomadosCollectionNew) {
                diplomadosCollectionNewDiplomadosToAttach = em.getReference(diplomadosCollectionNewDiplomadosToAttach.getClass(), diplomadosCollectionNewDiplomadosToAttach.getIdDiplomado());
                attachedDiplomadosCollectionNew.add(diplomadosCollectionNewDiplomadosToAttach);
            }
            diplomadosCollectionNew = attachedDiplomadosCollectionNew;
            ofertas.setDiplomadosCollection(diplomadosCollectionNew);
            ofertas = em.merge(ofertas);
            if (idDocenteOld != null && !idDocenteOld.equals(idDocenteNew)) {
                idDocenteOld.getOfertasCollection().remove(ofertas);
                idDocenteOld = em.merge(idDocenteOld);
            }
            if (idDocenteNew != null && !idDocenteNew.equals(idDocenteOld)) {
                idDocenteNew.getOfertasCollection().add(ofertas);
                idDocenteNew = em.merge(idDocenteNew);
            }
            if (talleresNew != null && !talleresNew.equals(talleresOld)) {
                Ofertas oldOfertasOfTalleres = talleresNew.getOfertas();
                if (oldOfertasOfTalleres != null) {
                    oldOfertasOfTalleres.setTalleres(null);
                    oldOfertasOfTalleres = em.merge(oldOfertasOfTalleres);
                }
                talleresNew.setOfertas(ofertas);
                talleresNew = em.merge(talleresNew);
            }
            for (Participaciones participacionesCollectionNewParticipaciones : participacionesCollectionNew) {
                if (!participacionesCollectionOld.contains(participacionesCollectionNewParticipaciones)) {
                    Ofertas oldIdOfertaOfParticipacionesCollectionNewParticipaciones = participacionesCollectionNewParticipaciones.getIdOferta();
                    participacionesCollectionNewParticipaciones.setIdOferta(ofertas);
                    participacionesCollectionNewParticipaciones = em.merge(participacionesCollectionNewParticipaciones);
                    if (oldIdOfertaOfParticipacionesCollectionNewParticipaciones != null && !oldIdOfertaOfParticipacionesCollectionNewParticipaciones.equals(ofertas)) {
                        oldIdOfertaOfParticipacionesCollectionNewParticipaciones.getParticipacionesCollection().remove(participacionesCollectionNewParticipaciones);
                        oldIdOfertaOfParticipacionesCollectionNewParticipaciones = em.merge(oldIdOfertaOfParticipacionesCollectionNewParticipaciones);
                    }
                }
            }
            for (Cursos cursosCollectionNewCursos : cursosCollectionNew) {
                if (!cursosCollectionOld.contains(cursosCollectionNewCursos)) {
                    Ofertas oldIdOfertaOfCursosCollectionNewCursos = cursosCollectionNewCursos.getIdOferta();
                    cursosCollectionNewCursos.setIdOferta(ofertas);
                    cursosCollectionNewCursos = em.merge(cursosCollectionNewCursos);
                    if (oldIdOfertaOfCursosCollectionNewCursos != null && !oldIdOfertaOfCursosCollectionNewCursos.equals(ofertas)) {
                        oldIdOfertaOfCursosCollectionNewCursos.getCursosCollection().remove(cursosCollectionNewCursos);
                        oldIdOfertaOfCursosCollectionNewCursos = em.merge(oldIdOfertaOfCursosCollectionNewCursos);
                    }
                }
            }
            for (Seminarios seminariosCollectionNewSeminarios : seminariosCollectionNew) {
                if (!seminariosCollectionOld.contains(seminariosCollectionNewSeminarios)) {
                    Ofertas oldIdOfertaOfSeminariosCollectionNewSeminarios = seminariosCollectionNewSeminarios.getIdOferta();
                    seminariosCollectionNewSeminarios.setIdOferta(ofertas);
                    seminariosCollectionNewSeminarios = em.merge(seminariosCollectionNewSeminarios);
                    if (oldIdOfertaOfSeminariosCollectionNewSeminarios != null && !oldIdOfertaOfSeminariosCollectionNewSeminarios.equals(ofertas)) {
                        oldIdOfertaOfSeminariosCollectionNewSeminarios.getSeminariosCollection().remove(seminariosCollectionNewSeminarios);
                        oldIdOfertaOfSeminariosCollectionNewSeminarios = em.merge(oldIdOfertaOfSeminariosCollectionNewSeminarios);
                    }
                }
            }
            for (Inscripcion inscripcionCollectionNewInscripcion : inscripcionCollectionNew) {
                if (!inscripcionCollectionOld.contains(inscripcionCollectionNewInscripcion)) {
                    Ofertas oldOfertasOfInscripcionCollectionNewInscripcion = inscripcionCollectionNewInscripcion.getOfertas();
                    inscripcionCollectionNewInscripcion.setOfertas(ofertas);
                    inscripcionCollectionNewInscripcion = em.merge(inscripcionCollectionNewInscripcion);
                    if (oldOfertasOfInscripcionCollectionNewInscripcion != null && !oldOfertasOfInscripcionCollectionNewInscripcion.equals(ofertas)) {
                        oldOfertasOfInscripcionCollectionNewInscripcion.getInscripcionCollection().remove(inscripcionCollectionNewInscripcion);
                        oldOfertasOfInscripcionCollectionNewInscripcion = em.merge(oldOfertasOfInscripcionCollectionNewInscripcion);
                    }
                }
            }
            for (Certificaciones certificacionesCollectionNewCertificaciones : certificacionesCollectionNew) {
                if (!certificacionesCollectionOld.contains(certificacionesCollectionNewCertificaciones)) {
                    Ofertas oldIdOfertaOfCertificacionesCollectionNewCertificaciones = certificacionesCollectionNewCertificaciones.getIdOferta();
                    certificacionesCollectionNewCertificaciones.setIdOferta(ofertas);
                    certificacionesCollectionNewCertificaciones = em.merge(certificacionesCollectionNewCertificaciones);
                    if (oldIdOfertaOfCertificacionesCollectionNewCertificaciones != null && !oldIdOfertaOfCertificacionesCollectionNewCertificaciones.equals(ofertas)) {
                        oldIdOfertaOfCertificacionesCollectionNewCertificaciones.getCertificacionesCollection().remove(certificacionesCollectionNewCertificaciones);
                        oldIdOfertaOfCertificacionesCollectionNewCertificaciones = em.merge(oldIdOfertaOfCertificacionesCollectionNewCertificaciones);
                    }
                }
            }
            for (Diplomados diplomadosCollectionNewDiplomados : diplomadosCollectionNew) {
                if (!diplomadosCollectionOld.contains(diplomadosCollectionNewDiplomados)) {
                    Ofertas oldIdOfertaOfDiplomadosCollectionNewDiplomados = diplomadosCollectionNewDiplomados.getIdOferta();
                    diplomadosCollectionNewDiplomados.setIdOferta(ofertas);
                    diplomadosCollectionNewDiplomados = em.merge(diplomadosCollectionNewDiplomados);
                    if (oldIdOfertaOfDiplomadosCollectionNewDiplomados != null && !oldIdOfertaOfDiplomadosCollectionNewDiplomados.equals(ofertas)) {
                        oldIdOfertaOfDiplomadosCollectionNewDiplomados.getDiplomadosCollection().remove(diplomadosCollectionNewDiplomados);
                        oldIdOfertaOfDiplomadosCollectionNewDiplomados = em.merge(oldIdOfertaOfDiplomadosCollectionNewDiplomados);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ofertas.getIdOferta();
                if (findOfertas(id) == null) {
                    throw new NonexistentEntityException("The ofertas with id " + id + " no longer exists.");
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
            Ofertas ofertas;
            try {
                ofertas = em.getReference(Ofertas.class, id);
                ofertas.getIdOferta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ofertas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Talleres talleresOrphanCheck = ofertas.getTalleres();
            if (talleresOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ofertas (" + ofertas + ") cannot be destroyed since the Talleres " + talleresOrphanCheck + " in its talleres field has a non-nullable ofertas field.");
            }
            Collection<Participaciones> participacionesCollectionOrphanCheck = ofertas.getParticipacionesCollection();
            for (Participaciones participacionesCollectionOrphanCheckParticipaciones : participacionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ofertas (" + ofertas + ") cannot be destroyed since the Participaciones " + participacionesCollectionOrphanCheckParticipaciones + " in its participacionesCollection field has a non-nullable idOferta field.");
            }
            Collection<Cursos> cursosCollectionOrphanCheck = ofertas.getCursosCollection();
            for (Cursos cursosCollectionOrphanCheckCursos : cursosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ofertas (" + ofertas + ") cannot be destroyed since the Cursos " + cursosCollectionOrphanCheckCursos + " in its cursosCollection field has a non-nullable idOferta field.");
            }
            Collection<Seminarios> seminariosCollectionOrphanCheck = ofertas.getSeminariosCollection();
            for (Seminarios seminariosCollectionOrphanCheckSeminarios : seminariosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ofertas (" + ofertas + ") cannot be destroyed since the Seminarios " + seminariosCollectionOrphanCheckSeminarios + " in its seminariosCollection field has a non-nullable idOferta field.");
            }
            Collection<Inscripcion> inscripcionCollectionOrphanCheck = ofertas.getInscripcionCollection();
            for (Inscripcion inscripcionCollectionOrphanCheckInscripcion : inscripcionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ofertas (" + ofertas + ") cannot be destroyed since the Inscripcion " + inscripcionCollectionOrphanCheckInscripcion + " in its inscripcionCollection field has a non-nullable ofertas field.");
            }
            Collection<Certificaciones> certificacionesCollectionOrphanCheck = ofertas.getCertificacionesCollection();
            for (Certificaciones certificacionesCollectionOrphanCheckCertificaciones : certificacionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ofertas (" + ofertas + ") cannot be destroyed since the Certificaciones " + certificacionesCollectionOrphanCheckCertificaciones + " in its certificacionesCollection field has a non-nullable idOferta field.");
            }
            Collection<Diplomados> diplomadosCollectionOrphanCheck = ofertas.getDiplomadosCollection();
            for (Diplomados diplomadosCollectionOrphanCheckDiplomados : diplomadosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ofertas (" + ofertas + ") cannot be destroyed since the Diplomados " + diplomadosCollectionOrphanCheckDiplomados + " in its diplomadosCollection field has a non-nullable idOferta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Docente idDocente = ofertas.getIdDocente();
            if (idDocente != null) {
                idDocente.getOfertasCollection().remove(ofertas);
                idDocente = em.merge(idDocente);
            }
            em.remove(ofertas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ofertas> findOfertasEntities() {
        return findOfertasEntities(true, -1, -1);
    }

    public List<Ofertas> findOfertasEntities(int maxResults, int firstResult) {
        return findOfertasEntities(false, maxResults, firstResult);
    }

    private List<Ofertas> findOfertasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ofertas.class));
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

    public Ofertas findOfertas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ofertas.class, id);
        } finally {
            em.close();
        }
    }

    public int getOfertasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ofertas> rt = cq.from(Ofertas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
