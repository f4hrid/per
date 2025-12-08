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
import model.Estudiante;
import model.Administrador;
import model.Docente;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

/**
 *
 * @author Fahrid
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiante estudiante = usuario.getEstudiante();
            if (estudiante != null) {
                estudiante = em.getReference(estudiante.getClass(), estudiante.getIdEstudiante());
                usuario.setEstudiante(estudiante);
            }
            Administrador administrador = usuario.getAdministrador();
            if (administrador != null) {
                administrador = em.getReference(administrador.getClass(), administrador.getIdAdministrador());
                usuario.setAdministrador(administrador);
            }
            Docente docente = usuario.getDocente();
            if (docente != null) {
                docente = em.getReference(docente.getClass(), docente.getIdDocente());
                usuario.setDocente(docente);
            }
            em.persist(usuario);
            if (estudiante != null) {
                Usuario oldIdUsuarioOfEstudiante = estudiante.getIdUsuario();
                if (oldIdUsuarioOfEstudiante != null) {
                    oldIdUsuarioOfEstudiante.setEstudiante(null);
                    oldIdUsuarioOfEstudiante = em.merge(oldIdUsuarioOfEstudiante);
                }
                estudiante.setIdUsuario(usuario);
                estudiante = em.merge(estudiante);
            }
            if (administrador != null) {
                Usuario oldIdUsuarioOfAdministrador = administrador.getIdUsuario();
                if (oldIdUsuarioOfAdministrador != null) {
                    oldIdUsuarioOfAdministrador.setAdministrador(null);
                    oldIdUsuarioOfAdministrador = em.merge(oldIdUsuarioOfAdministrador);
                }
                administrador.setIdUsuario(usuario);
                administrador = em.merge(administrador);
            }
            if (docente != null) {
                Usuario oldIdUsuarioOfDocente = docente.getIdUsuario();
                if (oldIdUsuarioOfDocente != null) {
                    oldIdUsuarioOfDocente.setDocente(null);
                    oldIdUsuarioOfDocente = em.merge(oldIdUsuarioOfDocente);
                }
                docente.setIdUsuario(usuario);
                docente = em.merge(docente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            Estudiante estudianteOld = persistentUsuario.getEstudiante();
            Estudiante estudianteNew = usuario.getEstudiante();
            Administrador administradorOld = persistentUsuario.getAdministrador();
            Administrador administradorNew = usuario.getAdministrador();
            Docente docenteOld = persistentUsuario.getDocente();
            Docente docenteNew = usuario.getDocente();
            List<String> illegalOrphanMessages = null;
            if (estudianteOld != null && !estudianteOld.equals(estudianteNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Estudiante " + estudianteOld + " since its idUsuario field is not nullable.");
            }
            if (administradorOld != null && !administradorOld.equals(administradorNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Administrador " + administradorOld + " since its idUsuario field is not nullable.");
            }
            if (docenteOld != null && !docenteOld.equals(docenteNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Docente " + docenteOld + " since its idUsuario field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (estudianteNew != null) {
                estudianteNew = em.getReference(estudianteNew.getClass(), estudianteNew.getIdEstudiante());
                usuario.setEstudiante(estudianteNew);
            }
            if (administradorNew != null) {
                administradorNew = em.getReference(administradorNew.getClass(), administradorNew.getIdAdministrador());
                usuario.setAdministrador(administradorNew);
            }
            if (docenteNew != null) {
                docenteNew = em.getReference(docenteNew.getClass(), docenteNew.getIdDocente());
                usuario.setDocente(docenteNew);
            }
            usuario = em.merge(usuario);
            if (estudianteNew != null && !estudianteNew.equals(estudianteOld)) {
                Usuario oldIdUsuarioOfEstudiante = estudianteNew.getIdUsuario();
                if (oldIdUsuarioOfEstudiante != null) {
                    oldIdUsuarioOfEstudiante.setEstudiante(null);
                    oldIdUsuarioOfEstudiante = em.merge(oldIdUsuarioOfEstudiante);
                }
                estudianteNew.setIdUsuario(usuario);
                estudianteNew = em.merge(estudianteNew);
            }
            if (administradorNew != null && !administradorNew.equals(administradorOld)) {
                Usuario oldIdUsuarioOfAdministrador = administradorNew.getIdUsuario();
                if (oldIdUsuarioOfAdministrador != null) {
                    oldIdUsuarioOfAdministrador.setAdministrador(null);
                    oldIdUsuarioOfAdministrador = em.merge(oldIdUsuarioOfAdministrador);
                }
                administradorNew.setIdUsuario(usuario);
                administradorNew = em.merge(administradorNew);
            }
            if (docenteNew != null && !docenteNew.equals(docenteOld)) {
                Usuario oldIdUsuarioOfDocente = docenteNew.getIdUsuario();
                if (oldIdUsuarioOfDocente != null) {
                    oldIdUsuarioOfDocente.setDocente(null);
                    oldIdUsuarioOfDocente = em.merge(oldIdUsuarioOfDocente);
                }
                docenteNew.setIdUsuario(usuario);
                docenteNew = em.merge(docenteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Estudiante estudianteOrphanCheck = usuario.getEstudiante();
            if (estudianteOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Estudiante " + estudianteOrphanCheck + " in its estudiante field has a non-nullable idUsuario field.");
            }
            Administrador administradorOrphanCheck = usuario.getAdministrador();
            if (administradorOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Administrador " + administradorOrphanCheck + " in its administrador field has a non-nullable idUsuario field.");
            }
            Docente docenteOrphanCheck = usuario.getDocente();
            if (docenteOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Docente " + docenteOrphanCheck + " in its docente field has a non-nullable idUsuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
