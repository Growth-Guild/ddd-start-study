package edu.study.order.domain;

import java.util.Objects;

public class ShippingInfo {
    private final Address address;
    private final Receiver receiver;

    public ShippingInfo(String zipCode, String address, String receiverName, String receiverPhoneNumber) {
        this.address = new Address(zipCode, address);
        this.receiver = new Receiver(receiverName, receiverPhoneNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippingInfo that = (ShippingInfo) o;
        return Objects.equals(address, that.address) && Objects.equals(receiver, that.receiver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, receiver);
    }
}
