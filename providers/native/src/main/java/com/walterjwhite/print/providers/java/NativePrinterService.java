package com.walterjwhite.print.providers.java;

import com.walterjwhite.datastore.criteria.Repository;
import com.walterjwhite.file.api.service.FileStorageService;
import com.walterjwhite.google.guice.property.enumeration.NoOperation;
import com.walterjwhite.google.guice.property.property.Property;
import com.walterjwhite.print.api.service.PrinterRepository;
import com.walterjwhite.print.impl.AbstractPrinterService;
import com.walterjwhite.print.model.PrintJob;
import com.walterjwhite.print.model.PrintRequest;
import com.walterjwhite.print.model.Printer;
import java.awt.print.Pageable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Arrays;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NativePrinterService extends AbstractPrinterService {
  private static final Logger LOGGER = LoggerFactory.getLogger(NativePrinterService.class);

  // protected final PrintJobRepository printJobRepository;

  @Inject
  public NativePrinterService(
      FileStorageService fileStorageService,
      @Property(NoOperation.class) boolean isNooperation,
      Provider<PrinterRepository> printerRepositoryProvider,
      Provider<Repository> repositoryProvider) {
    super(isNooperation, fileStorageService, repositoryProvider, printerRepositoryProvider);
  }

  @Override
  protected void doPrint(PrintJob printJob) throws Exception {
    // printJobRepository.save(printJob);

    doPrint(printJob.getPrinter(), getPDFPageable(printJob.getPrintRequest()));
    // getPrinterService(printer).print(printer, );
  }

  protected PDFPageable getPDFPageable(PrintRequest printRequest) throws Exception {
    if (printRequest.getPassword() != null && !printRequest.getPassword().isEmpty()) {
      return (new PDFPageable(
          PDDocument.load(
              new java.io.File(printRequest.getFile().getSource()), printRequest.getPassword())));
    }

    return (new PDFPageable(PDDocument.load(new java.io.File(printRequest.getFile().getSource()))));
  }

  protected void doPrint(Printer printer, final Pageable pageable)
      throws PrintException, PrinterException {
    java.awt.print.PrinterJob job = PrinterJob.getPrinterJob();
    job.setPrintService(getPrinterService(printer));
    job.setPageable(pageable);

    job.print();
  }

  protected javax.print.PrintService getPrinterService(final Printer printer) {
    DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
    PrintRequestAttributeSet printRequestAttributes = new HashPrintRequestAttributeSet();
    printRequestAttributes.add(Sides.DUPLEX);

    javax.print.PrintService[] printServices =
        PrintServiceLookup.lookupPrintServices(flavor, printRequestAttributes);
    if (printServices.length == 0) {
      throw new IllegalStateException("No Printer found");
    }
    LOGGER.info("Available printers: " + Arrays.asList(printServices));

    javax.print.PrintService selectedPrintService = null;

    if (printer.getName() != null) {
      LOGGER.info("printer:" + printer.getName());

      for (javax.print.PrintService printService : printServices) {
        LOGGER.info("printer service:" + printService.getName());

        if (printService.getName().equals(printer.getName())) {
          selectedPrintService = printService;
          break;
        }
      }
    } else {
      selectedPrintService = printServices[0];
    }

    if (selectedPrintService == null) {
      throw new IllegalStateException("Printer not found");
    }

    return (selectedPrintService);
  }
}
