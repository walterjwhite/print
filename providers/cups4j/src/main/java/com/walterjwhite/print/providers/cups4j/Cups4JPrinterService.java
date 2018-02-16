package com.walterjwhite.print.providers.cups4j;

import com.walterjwhite.datastore.criteria.Repository;
import com.walterjwhite.file.api.service.FileStorageService;
import com.walterjwhite.google.guice.property.enumeration.NoOperation;
import com.walterjwhite.google.guice.property.property.Property;
import com.walterjwhite.print.api.service.PrinterRepository;
import com.walterjwhite.print.impl.AbstractPrinterService;
import com.walterjwhite.print.model.PrintJob;
import java.io.FileInputStream;
import java.net.URL;
import javax.inject.Inject;
import javax.inject.Provider;
import org.cups4j.CupsClient;
import org.cups4j.CupsPrinter;

public class Cups4JPrinterService extends AbstractPrinterService {
  protected final CupsClient cupsClient;

  @Inject
  public Cups4JPrinterService(
      Provider<Repository> repositoryProvider,
      FileStorageService fileStorageService,
      Provider<PrinterRepository> printerRepositoryProvider,
      CupsClient cupsClient,
      @Property(NoOperation.class) boolean isNooperation) {
    super(isNooperation, fileStorageService, repositoryProvider, printerRepositoryProvider);
    this.cupsClient = cupsClient;
  }

  @Override
  protected void doPrint(PrintJob printJob) throws Exception {
    getCupsPrinter(printJob)
        .print(
            new org.cups4j.PrintJob.Builder(
                    new FileInputStream(printJob.getPrintRequest().getFile().getSource()))
                .build());
  }

  protected CupsPrinter getCupsPrinter(PrintJob printJob) throws Exception {
    return cupsClient.getPrinter(new URL(printJob.getPrinter().getUri()));
  }
}
