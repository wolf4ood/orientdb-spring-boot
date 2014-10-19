package org.wolfwood.example.model;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OSchema;
import com.orientechnologies.orient.core.metadata.schema.OType;

public class TodoSchema {

  public static void createSchema(ODatabaseDocumentTx db) {

    if (!db.exists()) {

      db.create();
      OSchema schema = db.getMetadata().getSchema();

      OClass v = schema.getClass("V");

      OClass todo = schema.createClass(Todo.class);
      todo.setSuperClass(v);
      todo.createProperty(Todo.DESCRIPTION.toString(), OType.STRING);
      todo.createProperty(Todo.CREATED_AT.toString(), OType.DATETIME);

    }

  }

  public enum Todo {
    DESCRIPTION("description"), CREATED_AT("createdAt");
    private final String description;

    Todo(String description) {
      this.description = description;
    }

    @Override
    public String toString() {
      return description;
    }
  }

}
