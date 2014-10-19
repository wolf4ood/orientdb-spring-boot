package org.wolfwood.example.repositories;

import com.orientechnologies.orient.core.record.impl.ODocument;

import java.util.List;

/**
 * Created by enricorisa on 12/10/14.
 */
public interface BaseRepository<T> {

  public T save(T entity);

  public List<T> getAll();

  public T fromDoc(ODocument document);

  public ODocument toDoc(T entity);
}
