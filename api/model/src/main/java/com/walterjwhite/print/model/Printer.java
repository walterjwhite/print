package com.walterjwhite.print.model;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import com.walterjwhite.print.enumeration.PrinterType;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
// @PersistenceCapable
@NoArgsConstructor
@Entity
public class Printer extends AbstractNamedEntity {

  @ManyToOne @JoinColumn protected Location location;

  @Column(unique = true)
  protected String uri;

  @Enumerated(EnumType.STRING)
  @Column
  protected PrinterType printerType;

  public Printer(
      String name, String description, Location location, String uri, PrinterType printerType) {
    super(name, description);

    this.location = location;
    this.uri = uri;
    this.printerType = printerType;
  }
}
