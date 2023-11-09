package com.aaytugozkaya.springsecurity.res;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoResource {

    private Logger logger = LoggerFactory.getLogger(TodoResource.class);

    public static final List<Todo> TODOS_LIST = List.of(new Todo("aaytugozkaya", "Learn Spring Boot"),
            new Todo("aaytugozkaya", "Learn React"),
            new Todo("aaytugozkaya", "Learn AWS"));

    @GetMapping("/todos")
    public List<Todo> retrieveAllTodos() {
        return TODOS_LIST;
    }

    @GetMapping("/users/{username}/todos")
    public Todo retrieveTodosForASpecificUser(@PathVariable String username) {
        return TODOS_LIST.get(1)  ;
    }

    @PostMapping("/users/{username}/todos")
    public void CreateTodoForSpecificUser(@PathVariable String username, @RequestBody Todo todo) {
          logger.info("CreateTodoForSpecificUser: " + username + " " + todo);
    }
}

record Todo (String username, String description){}