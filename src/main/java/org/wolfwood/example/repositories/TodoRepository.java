package org.wolfwood.example.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.orientechnologies.orient.core.id.ORecordId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.wolfwood.example.OrientDBFactory;
import org.wolfwood.example.model.TodoSchema;
import org.wolfwood.example.model.domain.Todo;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;

@Repository
public class TodoRepository implements BaseRepository<Todo> {

  @Autowired
  private OrientDBFactory orientDBFactory;

  @Override
  public Todo save(Todo entity) {

    ODatabaseDocumentTx tx = orientDBFactory.getDB();
    ODocument doc = tx.save(toDoc(entity));

    return fromDoc(doc);
  }

  @Override
  public List<Todo> getAll() {

    ODatabaseDocumentTx tx = orientDBFactory.getDB();

    List<Todo> results = new ArrayList<Todo>();
    List<ODocument> docs = tx.query(new OSQLSynchQuery<ODocument>("select from Todo"));
    for (ODocument doc : docs) {
      results.add(fromDoc(doc));
    }
    return results;

  }

  @Override
  public Todo fromDoc(ODocument document) {

    Todo todo = new Todo();
    todo.setId(document.getIdentity().toString());
    todo.setCreatedAt((Date) document.field(TodoSchema.Todo.CREATED_AT.toString()));
    todo.setDescription((String) document.field(TodoSchema.Todo.DESCRIPTION.toString()));
    return todo;
  }

  @Override
  public ODocument toDoc(Todo entity) {

    ODocument doc;
    if (entity.getId() != null) {
      doc = orientDBFactory.getDB().load(new ORecordId(entity.getId()));
    } else {
      doc = new ODocument(Todo.class.getSimpleName());

    }
    doc.field(TodoSchema.Todo.DESCRIPTION.toString(), entity.getDescription());
    doc.field(TodoSchema.Todo.CREATED_AT.toString(), entity.getCreatedAt());

    return doc;
  }
}
