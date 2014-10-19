package org.wolfwood.example;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.tools.ant.taskdefs.Classloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.wolfwood.example.model.TodoSchema;

import com.orientechnologies.orient.core.Orient;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.server.OServer;
import com.orientechnologies.orient.server.OServerMain;

/**
 * Created by Enrico Risa on 18/10/14.
 */
@Configuration
public class OrientDBServerListener {

  private OServer                    server;

  @Autowired
  private OrientDBConnectionSettings settings;

  @PostConstruct
  public void atStartup() {

    try {
      server = OServerMain.create();
      server.startup(ClassLoader.getSystemResourceAsStream("orientdb-server-config.xml"));
      server.activate();
      ODatabaseDocumentTx tx = Orient.instance().getDatabaseFactory().createDatabase("graph", settings.getUrl());

      TodoSchema.createSchema(tx);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @PreDestroy
  public void atShutdown() {
    server.shutdown();
  }
}
