package org.wolfwood.example;

import javax.annotation.PostConstruct;

import com.orientechnologies.orient.core.db.ODatabasePoolBase;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentPool;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;

@Component
public class OrientDBFactory {

  private ODatabasePoolBase<ODatabaseDocumentTx> pool;

  private ThreadLocal<ODatabaseDocumentTx>       txThreadLocal = new ThreadLocal<ODatabaseDocumentTx>();
  @Autowired
  private OrientDBConnectionSettings             settings;

  public OrientDBFactory() {

  }

  @PostConstruct
  public void initFactory() {

    pool = new ODatabaseDocumentPool(settings.getUrl(), settings.getUsr(), settings.getPwd()).setup(1, 10);

  }

  public ODatabaseDocumentTx getDB() {

    ODatabaseDocumentTx tx = txThreadLocal.get();
    if (tx == null) {
      tx = pool.acquire();
    }

    return tx;
  }

}
