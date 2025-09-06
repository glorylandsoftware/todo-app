package org.example.todo;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;

@QuarkusTest
public class TodoResourceTest {

    @Test
    void testCreateAndGetTodos() {
        // Create Todo
//        given()
//                .contentType(ContentType.JSON)
//                .body("{\"title\":\"Integration Test Todo\"}")
//                .when().post("/todos")
//                .then()
//                .statusCode(200)
//                .body("title", is("Integration Test Todo"))
//                .body("completed", is(false));

        // Get Todos
//        given()
//                .when().get("/todos")
//                .then()
//                .statusCode(200)
//                .body("[0].title", is("Integration Test Todo"));

        given()
                .contentType(ContentType.JSON)
                .body("{\"title\":\"Integration Test Todo\"}")
                .when().post("/todos")
                .then()
                .statusCode(200)
                .body("title", is("Integration Test Todo"))
                .body("completed", is(false));

        // Verify "Integration Test Todo" exists in the list
        given()
                .when().get("/todos")
                .then()
                .statusCode(200)
                .body("title", hasItem("Integration Test Todo"));

    }
}
