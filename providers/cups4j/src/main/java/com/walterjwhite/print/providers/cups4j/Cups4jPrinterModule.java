package com.walterjwhite.print.providers.cups4j;

import com.google.inject.AbstractModule;
import com.walterjwhite.print.api.service.PrinterService;
import org.cups4j.CupsClient;

public class Cups4jPrinterModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(PrinterService.class).to(Cups4JPrinterService.class);
    bind(CupsClient.class).toProvider(CupsClientProvider.class);
  }
}
