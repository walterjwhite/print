package service;

import java.awt.print.PrinterException;
import java.io.IOException;
import javax.print.PrintException;

public class PrintTest {

  /**
   * The print job is not scaling and thus is a problem ...
   *
   * @throws IOException
   * @throws PrinterException
   * @throws PrintException
   */
  public static void main(final String[] arguments)
      throws IOException, PrinterException, PrintException {

    /*
    final DefaultPrinterRepositoryService printerRepository = new DefaultPrinterRepositoryService();
    final NativePrinterService printerService = new NativePrinterService();
    final DefaultClientPrintService clientPrintService = new DefaultClientPrintService(printerRepository, printerService);

    //clientPrintService.print(new File("label.pdf"), null, null);
    //clientPrintService.print("testing, 123.  If you can read this, great.\f".getBytes("UTF-8"), null, null);
    clientPrintService.print(new File("label.pdf"), null, null);

    // this only supports PDFs ...
    // TODO: add support for images, must implement Pageable interface
    //clientPrintService.print(new File("test-image.png"), null, null);
    */
  }
}
