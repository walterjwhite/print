package com.walterjwhite.print.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.file.api.model.File;
import com.walterjwhite.print.enumeration.PrinterType;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
public class PrintRequest extends AbstractEntity {
  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected Location location;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, updatable = false)
  protected PrinterType printerType;

  @ManyToOne
  @JoinColumn(nullable = false, updatable = false)
  protected File file;

  @Column(nullable = false, updatable = false)
  protected LocalDateTime requestDateTime;

  /** Password used to decrypt the document (if there is one). */
  protected String password;

  @Column protected byte[] passwordEncrypted;

  @Column protected byte[] passwordSalt;

  @OneToMany protected Set<PrintJob> printJobs = new HashSet<>();

  public PrintRequest(Location location, PrinterType printerType, File file, String password) {
    this();
    this.location = location;
    this.printerType = printerType;
    this.file = file;
    this.password = password;
  }

  public PrintRequest(Location location, PrinterType printerType, File file) {
    this(location, printerType, file, null);
  }

  public PrintRequest(File file, String password) {
    this(null, null, file, password);
  }

  public PrintRequest(File file) {
    this(file, null);
  }

  public PrintRequest() {
    super();
    requestDateTime = LocalDateTime.now();
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<PrintJob> getPrintJobs() {
    return printJobs;
  }

  public void setPrintJobs(Set<PrintJob> printJobs) {
    this.printJobs = printJobs;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public PrinterType getPrinterType() {
    return printerType;
  }

  public void setPrinterType(PrinterType printerType) {
    this.printerType = printerType;
  }

  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    this.file = file;
  }

  public LocalDateTime getRequestDateTime() {
    return requestDateTime;
  }

  public void setRequestDateTime(LocalDateTime requestDateTime) {
    this.requestDateTime = requestDateTime;
  }

  public byte[] getPasswordEncrypted() {
    return passwordEncrypted;
  }

  public void setPasswordEncrypted(byte[] passwordEncrypted) {
    this.passwordEncrypted = passwordEncrypted;
  }

  public byte[] getPasswordSalt() {
    return passwordSalt;
  }

  public void setPasswordSalt(byte[] passwordSalt) {
    this.passwordSalt = passwordSalt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PrintRequest that = (PrintRequest) o;

    if (location != null ? !location.equals(that.location) : that.location != null) return false;
    if (printerType != that.printerType) return false;
    if (file != null ? !file.equals(that.file) : that.file != null) return false;
    return requestDateTime != null
        ? requestDateTime.equals(that.requestDateTime)
        : that.requestDateTime == null;
  }

  @Override
  public int hashCode() {
    int result = location != null ? location.hashCode() : 0;
    result = 31 * result + (printerType != null ? printerType.hashCode() : 0);
    result = 31 * result + (file != null ? file.hashCode() : 0);
    result = 31 * result + (requestDateTime != null ? requestDateTime.hashCode() : 0);
    return result;
  }
}
