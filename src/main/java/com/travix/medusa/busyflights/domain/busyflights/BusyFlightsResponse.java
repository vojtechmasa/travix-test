package com.travix.medusa.busyflights.domain.busyflights;

import java.text.DecimalFormat;
import java.util.Objects;

public class BusyFlightsResponse {
  private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("#.00");

  private String airline;
  private String supplier;
  private double fare;
  private String departureAirportCode;
  private String destinationAirportCode;
  private String departureDate;
  private String arrivalDate;

  public BusyFlightsResponse() {}

  public String getAirline() {
    return airline;
  }

  public String getSupplier() {
    return supplier;
  }

  public String getFare() {
    return PRICE_FORMAT.format(fare);
  }

  public double fareAsDouble() {
    return fare;
  }

  public String getDepartureAirportCode() {
    return departureAirportCode;
  }

  public String getDestinationAirportCode() {
    return destinationAirportCode;
  }

  public String getDepartureDate() {
    return departureDate;
  }

  public String getArrivalDate() {
    return arrivalDate;
  }

  public void setAirline(String airline) {
    this.airline = airline;
  }

  public void setSupplier(String supplier) {
    this.supplier = supplier;
  }

  public void setFare(double fare) {
    this.fare = fare;
  }

  public void setDepartureAirportCode(String departureAirportCode) {
    this.departureAirportCode = departureAirportCode;
  }

  public void setDestinationAirportCode(String destinationAirportCode) {
    this.destinationAirportCode = destinationAirportCode;
  }

  public void setDepartureDate(String departureDate) {
    this.departureDate = departureDate;
  }

  public void setArrivalDate(String arrivalDate) {
    this.arrivalDate = arrivalDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BusyFlightsResponse)) return false;
    BusyFlightsResponse that = (BusyFlightsResponse) o;
    return Double.compare(that.fare, fare) == 0 &&
        Objects.equals(airline, that.airline) &&
        Objects.equals(supplier, that.supplier) &&
        Objects.equals(departureAirportCode, that.departureAirportCode) &&
        Objects.equals(destinationAirportCode, that.destinationAirportCode) &&
        Objects.equals(departureDate, that.departureDate) &&
        Objects.equals(arrivalDate, that.arrivalDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(airline, supplier, fare, departureAirportCode, destinationAirportCode, departureDate, arrivalDate);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("BusyFlightsResponse{");
    sb.append("airline='").append(airline).append('\'');
    sb.append(", supplier='").append(supplier).append('\'');
    sb.append(", fare=").append(fare);
    sb.append(", departureAirportCode='").append(departureAirportCode).append('\'');
    sb.append(", destinationAirportCode='").append(destinationAirportCode).append('\'');
    sb.append(", departureDate='").append(departureDate).append('\'');
    sb.append(", arrivalDate='").append(arrivalDate).append('\'');
    sb.append(", fareAsDouble=").append(fareAsDouble());
    sb.append('}');
    return sb.toString();
  }
}
