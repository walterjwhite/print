package com.walterjwhite.print.model;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import javax.persistence.Column;
import javax.persistence.Entity;

/** TODO: migrate this "location" stuff to another project and re-use it here. */
@Entity
public class Location extends AbstractNamedEntity {
  @Column protected String address;
  @Column protected String floor;
  @Column protected String room;
  @Column protected String zipCode;
  @Column protected String state;
  @Column protected String country;

  public Location(
      String name,
      String address,
      String floor,
      String room,
      String zipCode,
      String state,
      String country) {
    super(name);

    this.address = address;
    this.floor = floor;
    this.room = room;
    this.zipCode = zipCode;
    this.state = state;
    this.country = country;
  }

  public Location() {
    super();
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getFloor() {
    return floor;
  }

  public void setFloor(String floor) {
    this.floor = floor;
  }

  public String getRoom() {
    return room;
  }

  public void setRoom(String room) {
    this.room = room;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }
}
