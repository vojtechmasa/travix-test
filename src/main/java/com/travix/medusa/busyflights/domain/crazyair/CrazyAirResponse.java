package com.travix.medusa.busyflights.domain.crazyair;

import java.util.Objects;

public class CrazyAirResponse {

    private String airline;
    private double price;
    private String cabinclass;
    private String departureAirportCode;
    private String destinationAirportCode;
    private String departureDate;
    private String arrivalDate;

    public String getAirline() {
        return airline;
    }

    public void setAirline(final String airline) {
        this.airline = airline;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public String getCabinclass() {
        return cabinclass;
    }

    public void setCabinclass(final String cabinclass) {
        this.cabinclass = cabinclass;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(final String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    public void setDestinationAirportCode(final String destinationAirportCode) {
        this.destinationAirportCode = destinationAirportCode;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(final String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(final String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CrazyAirResponse)) return false;
        CrazyAirResponse that = (CrazyAirResponse) o;
        return Double.compare(that.price, price) == 0 &&
            Objects.equals(airline, that.airline) &&
            Objects.equals(cabinclass, that.cabinclass) &&
            Objects.equals(departureAirportCode, that.departureAirportCode) &&
            Objects.equals(destinationAirportCode, that.destinationAirportCode) &&
            Objects.equals(departureDate, that.departureDate) &&
            Objects.equals(arrivalDate, that.arrivalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airline, price, cabinclass, departureAirportCode, destinationAirportCode, departureDate, arrivalDate);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CrazyAirResponse{");
        sb.append("airline='").append(airline).append('\'');
        sb.append(", price=").append(price);
        sb.append(", cabinclass='").append(cabinclass).append('\'');
        sb.append(", departureAirportCode='").append(departureAirportCode).append('\'');
        sb.append(", destinationAirportCode='").append(destinationAirportCode).append('\'');
        sb.append(", departureDate='").append(departureDate).append('\'');
        sb.append(", arrivalDate='").append(arrivalDate).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
