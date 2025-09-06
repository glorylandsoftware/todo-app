package org.example.todo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class TodoRepository {

    @PersistenceContext
    EntityManager em;

    public List<Todo> findAll() {
        return em.createQuery("SELECT t FROM Todo t", Todo.class).getResultList();
    }

    public Todo findById(Long id) {
        return em.find(Todo.class, id);
    }

    public void persist(Todo todo) {
        em.persist(todo);
    }

    public Todo update(Todo todo) {
        return em.merge(todo);
    }

    public void delete(Todo todo) {
        if (em.contains(todo)) {
            em.remove(todo);
        } else {
            em.remove(em.merge(todo));
        }
    }
}
