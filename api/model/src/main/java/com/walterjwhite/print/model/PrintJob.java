package com.walterjwhite.print.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import javax.persistence.*;

@Entity
public class PrintJob extends AbstractEntity {
  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected Printer printer;

  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected PrintRequest printRequest;

  public PrintJob(Printer printer, PrintRequest printRequest) {
    super();
    this.printer = printer;
    this.printRequest = printRequest;
  }

  public Printer getPrinter() {
    return printer;
  }

  public void setPrinter(Printer printer) {
    this.printer = printer;
  }

  public PrintRequest getPrintRequest() {
    return printRequest;
  }

  public void setPrintRequest(PrintRequest printRequest) {
    this.printRequest = printRequest;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PrintJob printJob = (PrintJob) o;

    if (printer != null ? !printer.equals(printJob.printer) : printJob.printer != null)
      return false;
    return printRequest != null
        ? printRequest.equals(printJob.printRequest)
        : printJob.printRequest == null;
  }

  @Override
  public int hashCode() {
    int result = printer != null ? printer.hashCode() : 0;
    result = 31 * result + (printRequest != null ? printRequest.hashCode() : 0);
    return result;
  }
}
