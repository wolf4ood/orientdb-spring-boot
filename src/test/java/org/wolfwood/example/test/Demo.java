package org.wolfwood.example.test;

import javax.persistence.Id;
import java.util.Date;

public class Demo {

  @Id
  private Object id;

  private String description;
  private Date   createdAt;

  public Demo() {
  }

  public Object getId() {
    return id;
  }

  public void setId(Object id) {
    this.id = id;
  }

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
