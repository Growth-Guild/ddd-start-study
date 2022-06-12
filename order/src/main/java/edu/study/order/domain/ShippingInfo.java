package edu.study.order.domain;

import java.util.Objects;

public class ShippingInfo {
    private final String zipCode;
    private final String address;
    private final String receiverName;
    private final String receiverPhoneNumber;

    public ShippingInfo(String zipCode, String address, String receiverName, String receiverPhoneNumber) {
        this.zipCode = zipCode;
        this.address = address;
        this.receiverName = receiverName;
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippingInfo that = (ShippingInfo) o;
        return Objects.equals(zipCode, that.zipCode) && Objects.equals(address, that.address) && Objects.equals(receiverName, that.receiverName) && Objects.equals(receiverPhoneNumber, that.receiverPhoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, address, receiverName, receiverPhoneNumber);
    }
}
