package com.walterjwhite.print.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
// @PersistenceCapable
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PrintJob extends AbstractEntity {
  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected Printer printer;

  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected PrintRequest printRequest;
}
