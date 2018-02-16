package com.walterjwhite.print.api.service;

import com.walterjwhite.datastore.criteria.AbstractRepository;
import com.walterjwhite.datastore.criteria.CriteriaQueryConfiguration;
import com.walterjwhite.print.enumeration.PrinterType;
import com.walterjwhite.print.model.Location;
import com.walterjwhite.print.model.Printer;
import com.walterjwhite.print.model.Printer_;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

public class PrinterRepository extends AbstractRepository<Printer> {

  @Inject
  public PrinterRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
    super(entityManager, criteriaBuilder, Printer.class);
  }

  public List<Printer> findByLocationAndPrinterType(Location location, PrinterType printerType) {
    final CriteriaQueryConfiguration<Printer> printerCriteriaQueryConfiguration =
        getCriteriaQuery();

    Predicate locationPredicate =
        criteriaBuilder.equal(
            printerCriteriaQueryConfiguration.getRoot().get(Printer_.location), location);
    Predicate printerTypePredicate =
        criteriaBuilder.equal(
            printerCriteriaQueryConfiguration.getRoot().get(Printer_.printerType), printerType);

    Predicate where = criteriaBuilder.and(locationPredicate, printerTypePredicate);
    printerCriteriaQueryConfiguration.getCriteriaQuery().where(where);

    return (entityManager
        .createQuery(printerCriteriaQueryConfiguration.getCriteriaQuery())
        .getResultList());
  }

  public Printer getBest(Location location, PrinterType printerType) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public Printer findByNameAndLocationAndPrinterType(
      String name, Location location, PrinterType printerType) {
    final CriteriaQueryConfiguration<Printer> printerCriteriaQueryConfiguration =
        getCriteriaQuery();

    Predicate namePredicate =
        criteriaBuilder.equal(printerCriteriaQueryConfiguration.getRoot().get(Printer_.name), name);

    Predicate locationPredicate =
        criteriaBuilder.equal(
            printerCriteriaQueryConfiguration.getRoot().get(Printer_.location), location);
    Predicate printerTypePredicate =
        criteriaBuilder.equal(
            printerCriteriaQueryConfiguration.getRoot().get(Printer_.printerType), printerType);

    Predicate where = criteriaBuilder.and(namePredicate, locationPredicate, printerTypePredicate);
    printerCriteriaQueryConfiguration.getCriteriaQuery().where(where);

    return (entityManager
        .createQuery(printerCriteriaQueryConfiguration.getCriteriaQuery())
        .getSingleResult());
  }
}
