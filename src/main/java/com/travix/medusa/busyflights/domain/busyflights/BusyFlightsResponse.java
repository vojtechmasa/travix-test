package com.travix.medusa.busyflights.domain.busyflights;

public class BusyFlightsResponse {
  private String airline;
  private String supplier;
  private double fare; //should be rather BigDecimal
  private String departureAirportCode;
  private String destinationAirportCode;
  private String departureDate;
  private String arrivalDate;

  public BusyFlightsResponse() {}

  public BusyFlightsResponse(String airline, String supplier, double fare, String departureAirportCode,
                             String destinationAirportCode, String departureDate, String arrivalDate) {
    this.airline = airline;
    this.supplier = supplier;
    this.fare = fare;
    this.departureAirportCode = departureAirportCode;
    this.destinationAirportCode = destinationAirportCode;
    this.departureDate = departureDate;
    this.arrivalDate = arrivalDate;
  }

  public String getAirline() {
    return airline;
  }

  public String getSupplier() {
    return supplier;
  }

  public double getFare() {
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
}
