package org.wolfwood.example.test;

import java.util.Date;

import com.orientechnologies.orient.core.db.ODatabaseInternal;
import org.junit.Test;

import com.orientechnologies.orient.core.Orient;
import com.orientechnologies.orient.core.db.ODatabase;
import com.orientechnologies.orient.core.db.ODatabaseComplex;
import com.orientechnologies.orient.core.db.ODatabaseLifecycleListener;
import com.orientechnologies.orient.core.hook.ORecordHook;
import com.orientechnologies.orient.core.record.ORecord;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class OrientObjectTestHooks {

  @Test
  public void testHooks() {

    Orient.instance().addDbLifecycleListener(new OrientObjectHook());

    OObjectDatabaseTx db = new OObjectDatabaseTx("plocal:databases/testhooks");

    if (db.exists()) {
      db.open("admin", "admin");
      db.drop();

    }
    db.create();

    db.getEntityManager().registerEntityClasses("org.wolfwood.example");

    Demo todo = db.newInstance(Demo.class);
    todo.setDescription("Test");

    db.begin();
    db.save(todo);
    db.commit();

    db.begin();
    for (Object o : db.browseCluster("Demo")) {

      Demo t = (Demo) o;
      t.setCreatedAt(new Date());
      db.save(t);
    }
    db.commit();
    db.close();
  }

  public class OrientObjectHook implements ORecordHook, ODatabaseLifecycleListener {

    @Override
    public void onUnregister() {

    }

    @Override
    public RESULT onTrigger(TYPE iType, ORecord iRecord) {
      return RESULT.RECORD_NOT_CHANGED;
    }

    @Override
    public DISTRIBUTED_EXECUTION_MODE getDistributedExecutionMode() {
      return null;
    }

    @Override
    public PRIORITY getPriority() {
      return null;
    }

    @Override
    public void onCreate(ODatabaseInternal iDatabase) {

    }

    @Override
    public void onOpen(ODatabaseInternal iDatabase) {
      ((ODatabaseComplex<?>) iDatabase).registerHook(this);
    }

    @Override
    public void onClose(ODatabaseInternal iDatabase) {
      ((ODatabaseComplex<?>) iDatabase).unregisterHook(this);
    }
  }

  public class Demo {

    private String description;
    private Date   createdAt;

    public void setDescription(String description) {
      this.description = description;
    }

    public String getDescription() {
      return description;
    }

    public void setCreatedAt(Date createdAt) {
      this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
      return createdAt;
    }
  }
}
