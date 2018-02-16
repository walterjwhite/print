package service;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import javax.print.PrintService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Printer2Test {
  private static final Logger LOGGER = LoggerFactory.getLogger(Printer2Test.class);

  public static void main(final String[] arguments) throws IOException, PrinterException {
    PDDocument doc = PDDocument.load(new File("label.pdf"));
    PDFPageable pdfPageable = new PDFPageable(doc);

    // set the printer ...
    PrintService selectedPrintService = null;
    for (final PrintService printService : PrinterJob.lookupPrintServices()) {
      LOGGER.info("printer service:" + printService);
      if (printService.getName().contains("Default")) {
        selectedPrintService = printService;
        break;
      }
    }

    PrinterJob job = PrinterJob.getPrinterJob();
    job.setPrintService(selectedPrintService);
    LOGGER.info("selectedPrintService:" + selectedPrintService);
    job.setPageable(new PDFPageable(doc));
    // job.printDialog();

    // doc.sh

    // job.print();
  }
}
