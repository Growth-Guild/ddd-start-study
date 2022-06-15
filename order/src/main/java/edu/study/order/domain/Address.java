package edu.study.order.domain;

import java.util.Objects;

public class Address {
    private String zipCode;
    private String address;

    public Address(String zipCode, String address) {
        this.zipCode = zipCode;
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(zipCode, address1.zipCode) && Objects.equals(address, address1.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, address);
    }
}
