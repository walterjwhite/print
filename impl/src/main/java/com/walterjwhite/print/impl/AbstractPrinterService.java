package com.walterjwhite.print.impl;

import com.google.inject.persist.Transactional;
import com.walterjwhite.datastore.criteria.Repository;
import com.walterjwhite.file.api.service.FileStorageService;
import com.walterjwhite.print.api.service.PrinterRepository;
import com.walterjwhite.print.api.service.PrinterService;
import com.walterjwhite.print.model.PrintJob;
import com.walterjwhite.print.model.PrintRequest;
import com.walterjwhite.print.model.Printer;
import javax.inject.Provider;

public abstract class AbstractPrinterService implements PrinterService {
  protected final boolean isNooperation;

  protected final FileStorageService fileStorageService;

  protected final Provider<Repository> repositoryProvider;

  protected final Provider<PrinterRepository> printerRepositoryProvider;

  protected AbstractPrinterService(
      boolean isNooperation,
      FileStorageService fileStorageService,
      Provider<Repository> repositoryProvider,
      Provider<PrinterRepository> printerRepositoryProvider) {
    this.isNooperation = isNooperation;
    this.fileStorageService = fileStorageService;
    this.repositoryProvider = repositoryProvider;
    this.printerRepositoryProvider = printerRepositoryProvider;
  }

  @Transactional
  @Override
  public PrintJob print(PrintRequest printRequest) throws Exception {
    final Repository repository = repositoryProvider.get();

    if (printRequest.getId() == null) repository.persist(printRequest);

    PrintJob printJob = new PrintJob(getPrinter(printRequest), printRequest);
    repository.persist(printJob);

    if (!isNooperation) {
      // ensure we get a copy of the file locally before trying to print
      fileStorageService.get(printRequest.getFile());
      doPrint(printJob);
    }

    return (PrintJob) repository.merge(printJob);
  }

  protected Printer getPrinter(PrintRequest printRequest) {
    if (printRequest.getLocation() == null) {
      return printerRepositoryProvider
          .get()
          .findByLocationAndPrinterType(printRequest.getLocation(), null)
          .get(0);
    }

    throw new IllegalStateException("No printer chosen");
  }

  protected abstract void doPrint(PrintJob printJob) throws Exception;
}
