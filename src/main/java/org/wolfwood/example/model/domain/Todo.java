package org.wolfwood.example.model.domain;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by enricorisa on 12/10/14.
 */
public class Todo {

  private String id;
  @NotNull
  private String description;
  private Date   createdAt;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
