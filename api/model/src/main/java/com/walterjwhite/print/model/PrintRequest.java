package com.walterjwhite.print.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.file.api.model.File;
import com.walterjwhite.print.enumeration.PrinterType;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
// @PersistenceCapable
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
  @EqualsAndHashCode.Exclude @Transient protected transient String password;

  @EqualsAndHashCode.Exclude @Column protected String passwordEncrypted;

  @EqualsAndHashCode.Exclude @Column protected String passwordSalt;

  @EqualsAndHashCode.Exclude @OneToMany protected Set<PrintJob> printJobs = new HashSet<>();

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
}
