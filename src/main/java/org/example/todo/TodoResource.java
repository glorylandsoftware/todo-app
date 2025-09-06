package org.example.todo;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

import org.jboss.logging.Logger;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TodoResource {

    @Inject
    TodoRepository repository;

    @Inject
    Logger log;

    @GET
    public List<Todo> getAll() {
        return repository.findAll();
    }

    @POST
    @Transactional
    public Todo create(Todo todo) {
        log.info("/todos new request received: post " + todo.toString() );
        repository.persist(todo);
        return todo;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Todo update(@PathParam("id") Long id, Todo updated) {
        log.info("/id new request received: update " + id );
        Todo existing = repository.findById(id);
        if (existing == null) {
            throw new WebApplicationException("Todo not found", 404);
        }
        existing.setTitle(updated.getTitle());
        existing.setCompleted(updated.isCompleted());
        return repository.update(existing);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        log.info("/id new request received: delete " + id );
        Todo todo = repository.findById(id);
        if (todo != null) {
            repository.delete(todo);
        }
    }
}
