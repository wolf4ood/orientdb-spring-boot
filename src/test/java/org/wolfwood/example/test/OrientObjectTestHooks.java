package org.wolfwood.example.test;

import org.junit.Test;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class OrientObjectTestHooks {

  @Test
  public void testHooks() {

    OObjectDatabaseTx db = new OObjectDatabaseTx("plocal:databases/testhooks");

    if (db.exists()) {
      db.open("admin", "admin");
      db.drop();

    }
    db.create();

    db.getEntityManager().registerEntityClasses("org.wolfwood.example.test");

    Demo todo = new Demo();
    todo.setDescription("Test");

    todo = db.save(todo);

    todo = db.detach(todo, true);

    todo.setDescription("After Test");

    db.attach(todo);
    todo = db.save(todo);
    db.close();
  }

}
