package org.example.todo;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class TodoRepositoryTest {

    @Inject
    TodoRepository repository;

    @Test
    @Transactional
    void testPersistAndFind() {
        Todo todo = new Todo();
        todo.setTitle("Unit Test Todo");
        repository.persist(todo);

        List<Todo> todos = repository.findAll();
        assertTrue(todos.stream().anyMatch(t -> t.getTitle().equals("Unit Test Todo")));
    }
}
