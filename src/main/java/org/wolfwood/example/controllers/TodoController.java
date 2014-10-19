package org.wolfwood.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.wolfwood.example.model.domain.Todo;
import org.wolfwood.example.repositories.TodoRepository;
import org.wolfwood.example.services.TodoService;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class TodoController {

  @Autowired
  private TodoService    todoService;

  @Autowired
  private TodoRepository todoRepository;

  @RequestMapping(value = "/todos", method = RequestMethod.POST)
  public Todo createTodo(@RequestBody Todo todo) {
    return todoService.createTodo(todo);
  }

  @RequestMapping(value = "/todos", method = RequestMethod.GET)
  public List<Todo> all() {
    return todoRepository.getAll();
  }

  @RequestMapping(value = "/todos/{id}", method = RequestMethod.DELETE)
  public void delete(@PathVariable("id") String id) {
    todoService.detete(id);
  }
}
