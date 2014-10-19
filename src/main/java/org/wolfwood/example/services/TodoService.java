package org.wolfwood.example.services;

import org.wolfwood.example.model.domain.Todo;

public interface TodoService {

  public Todo createTodo(Todo todo);

  public void detete(String id);


}
