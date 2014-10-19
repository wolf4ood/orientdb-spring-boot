package org.wolfwood.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wolfwood.example.model.domain.Todo;
import org.wolfwood.example.repositories.TodoRepository;

import java.util.Date;

@Service
public class TodoServiceImpl implements TodoService {

  @Autowired
  private TodoRepository todoRepository;

  @Override
  public Todo createTodo(Todo todo) {

    todo.setCreatedAt(new Date());
    return todoRepository.save(todo);
  }

  @Override
  public void detete(String id) {

  }
}
