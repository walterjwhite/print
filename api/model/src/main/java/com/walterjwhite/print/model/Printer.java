package com.walterjwhite.print.model;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import com.walterjwhite.print.enumeration.PrinterType;
import javax.persistence.*;

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

  public Printer() {
    super();
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public PrinterType getPrinterType() {
    return printerType;
  }

  public void setPrinterType(PrinterType printerType) {
    this.printerType = printerType;
  }
}
